<template>
  <header class="header">
            <a href="#" class="logo"><router-link to="/Menu">Mercado FIPP</router-link></a>
    
            <nav class="navbarra">
              <a style="--i:1"><router-link to="/form-categorias/Categorias">Categoria</router-link></a>
              <a v-if="nivel==1" style="--i:2"><router-link to="/form-usuario/Usuário">Admin</router-link></a>
              <a v-if="nivel==2" style="--i:2"><router-link to="/form-usuario/Usuário">Usuario</router-link></a>
            </nav>
    </header>
  <h1 class="sub-title" style="margin-top: 100px;"><span>Anuncio</span></h1>
  <div class="hello" style="width: 60%; margin-left: auto; margin-right: auto; margin-bottom: 200px;">
    <div v-if="formOn">
      <form @submit.prevent="submitForm">
        <label for="idanun">Id</label>
        <input type="text" id="idanun" v-model="id" placeholder="ID do Anuncio..">

        <label for="title">Titulo</label>
        <input type="text" id="title" v-model="titulo" placeholder="Titulo do anuncio..">

        <label for="date">Data</label>
        <input type="date" id="date" v-model="data" placeholder="Data..">
        <label for="desc">Descrição</label>
        <input type="text" id="desc" v-model="descricao" placeholder="Descrição..">

        <label for="price">Preço</label>
        <input type="text" id="price" v-model="preco" placeholder="Preço..">

        <label for="imagem">Imagem</label>
        <input type="file" multiple @change="handleFiles" accept="image/*"/>
    
        <div v-if="fotosSelecionadas.length">
          <p><strong>Arquivos selecionados:</strong></p>
          <ul>
            <li v-for="(foto, index) in fotosSelecionadas" :key="index">{{ foto.name }}</li>
          </ul>
        </div>

        <label for="idcat">Id categoria</label>
        <input type="number" id="idcat" v-model="catid" placeholder="Id da categoria..">
        <label for="idusu">Id do usuario</label>
        <input type="number" id="idusu" v-model="usrid" placeholder="Id do usuario..">
        <input type="submit" value="Confirmar">
      </form>
    </div>

    <button @click="this.mostrarForm(true)">Novo Anuncio</button>

    <table id="add">
      <thead>
        <tr>
          <th>Id</th>
          <th @click="ordenarTitulo()">Titulo</th>
          <th>Data</th>
          <th>Descrição</th>
          <th>Preço</th>
          <th>Cat id</th>
          <th>Usu id</th>
          
          <th colspan="2">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="anu in this.anuncios" :key="anu.id">
          <td>{{ anu.id }}</td>
          <td>{{ anu.titulo }}</td>
          <td>{{ anu.data }}</td>
          <td>{{ anu.descricao }}</td>
          <td>{{ anu.preco }}</td>
          <td>{{ anu.catid }}</td>
          <td>{{ anu.usrid }}</td>
          <td><button @click="this.alterar(anu.id)">Alterar</button></td>
          <td v-if="nivel == 1"><button @click="this.apagar(anu.id)">Apagar</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FormAnuncio',
  data() {
    return {
      id: 0, titulo: "", data: "", descricao: "", preco: "", catid: 0, usrid: 0, nivel:0, modoAlterar: false, formOn: false, anuncios: [], fotosSelecionadas: []
    }
  },
  created(){
    this.nivel = this.$route.query.nivel;
  },
 methods: {
  mostrarForm(flag) {
    this.formOn = flag;
  },
  submitForm() {
    if (this.modoAlterar) {
      this.alterarAnuncio();
    } else {
      this.gravar();
    }
  },
  gravar() {
  const url = 'http://localhost:8080/apis/anuncio';
  const formData = new FormData();

  // Adiciona os dados do anúncio como um JSON convertido para Blob
  const anuncio = {
    id: this.id || null,
    titulo: this.titulo,
    data: this.data ? new Date(this.data).toISOString().substring(0, 10) : null,
    descricao: this.descricao,
    preco: this.preco,
    catid: this.catid,
    usrid: this.usrid
  };

  formData.append('anuncio', new Blob([JSON.stringify(anuncio)], { type: 'application/json' }));

  // Adiciona as imagens selecionadas
  this.fotosSelecionadas.forEach(foto => {
    formData.append('fotos', foto); // Mesmo nome "fotos" para o backend reconhecer como lista
  });

  axios.post(url, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  .then(response => {
    this.carregarDados();
    this.mostrarForm(false);
  })
  .catch(error => {
    console.error('Erro ao gravar anúncio:', error.response || error);
    alert('Erro ao gravar anúncio');
  });
},
  apagar(id) {
    axios.delete("http://localhost:8080/apis/anuncio/" + id)
      .then(() => this.carregarDados())
      .catch(error => alert('Erro ao apagar: ' + error));
  },
  alterar(id) {
    this.formOn = true;
    axios.get("http://localhost:8080/apis/anuncio/" + id)
      .then(result => {
        const anuncio = result.data;
        this.id = anuncio.id;
        this.titulo = anuncio.titulo;
        this.data = anuncio.data ? anuncio.data.substring(0,10) : ""; // para input type=date
        this.descricao = anuncio.descricao;
        this.preco = anuncio.preco;
        this.catid = anuncio.catid;
        this.usrid = anuncio.usrid;
        this.modoAlterar = true;
        this.mostrarForm(true);
      })
      .catch(error => alert('Erro ao carregar anuncio: ' + error));
  },
  alterarAnuncio() {
    const url = `http://localhost:8080/apis/anuncio`;
    const data = {
      id: this.id,  // importante enviar o id para update
      titulo: this.titulo,
      data: this.data ? new Date(this.data).toISOString().substring(0, 10) : null,
      descricao: this.descricao,
      preco: this.preco,
      catid: this.catid,
      usrid: this.usrid
    };
    axios.put(url, data)
      .then(() => {
        this.carregarDados();
        this.limparForm();
        this.mostrarForm(false);
        this.modoAlterar = false;
      })
      .catch(error => {
        alert("Erro ao alterar anúncio: " + error);
      });
  },
  limparForm() {
    this.id = 0;
    this.titulo = "";
    this.data = "";
    this.descricao = "";
    this.preco = "";
    this.catid = "";
    this.usrid = "";
    this.modoAlterar = false;
  },
  carregarDados() {
    axios.get("http://localhost:8080/apis/anuncio")
      .then(result => { this.anuncios = result.data; })
      .catch(error => alert('Erro ao carregar anúncios: ' + error));
  },
  ordenarTitulo() {
    this.anuncios.sort((a, b) => a.titulo.localeCompare(b.titulo));
  },
  handleFiles(event){
    this.fotosSelecionadas = Array.from(event.target.files); //converte FileList para array
  }
},
  mounted(){
      this.carregarDados();
  }
}
</script>

<style scoped>
/* Estilização geral */
.hello {
  width: 90%;
  max-width: 900px;
  margin: 0 auto 200px auto;
  background-color: #f5f7ff;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Formulário */
form {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

input[type="text"],
input[type="date"],
select {
  padding: 10px 15px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 16px;
}

input[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 12px;
  font-size: 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s;
}

input[type="submit"]:hover {
  background-color: #45a049;
}

/* Botão novo anúncio */
button {
  margin-top: 20px;
  background-color: #5e72e4;
  color: white;
  padding: 10px 20px;
  font-size: 15px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #4051b5;
}

/* Tabela */
#add {
  width: 100%;
  border-collapse: collapse;
  margin-top: 30px;
  font-size: 15px;
}

#add th,
#add td {
  text-align: left;
  padding: 12px;
  border: 1px solid #ddd;
  color: black;
}

#add th {
  background-color: #4CAF50;
  color: black;
}

#add tr:nth-child(even) {
  background-color: #91a1ff;
}

#add tr:hover {
  background-color: #1212;
}

#add td button {
  background-color: #e74c3c;
  color: white;
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
}

#add td button:hover {
  background-color: #c0392b;
}

/* Título */
.sub-title {
  text-align: center;
  font-size: 36px;
  margin-top: 120px;
  margin-bottom: 40px;
  font-weight: bold;
  color: #333;
}

.sub-title span {
  color: #4051b5;
}

</style>
