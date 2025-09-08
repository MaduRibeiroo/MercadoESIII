<template>
        <header class="header">
            <a v-if="nivel == 2" href="#" class="logo" @click="irParaMenu">Mercado FIPP</a>
            <a v-if="nivel == 1" href="#" class="logo"@click="irParaMenuAdm">Mercado FIPP</a>
            <nav class="navbarra">
              <a v-if="nivel==1" style="--i:2"><router-link to="/form-usuario/Usuário">Admin</router-link></a>
              <a v-if="nivel==2" style="--i:2"><router-link to="/form-usuario/Usuário">Usuario</router-link></a>
            </nav>
        </header>
    
        <main class="main-content">
            <div class="formContent">
                <h3 class="titulo-principal">ANÚNCIOS</h3>
                <p class="titulo-instrucao">Busque o anúncio pelo nome</p>
                <form class="form-pesquisa" @submit.prevent="buscarTitulo">
                    <input type="text" id="buscar" placeholder="Digite o título..." v-model="titulo">
                    <button id="botaoPesquisar" type="submit">Pesquisar</button>
                    <p v-if="mensagem" style="color: #ff9999; margin-top: 20px;">{{ mensagem }}</p>
                </form>
            </div>
            <div class="divAnuncios">
                <div v-for="(anu, index) in anuncios" :key="anu.id" class="cards">
                    <h3>{{ anu.titulo}}</h3>
                    <div class="carousel" v-if="anu.fotos.length > 0">
                      <button class="btnPrev" @click="prevImage(index)">‹</button>
                      <img :src="anu.fotos[currentPhotoIndices[anu.id] || 0]" alt="Foto do anúncio" class="carousel-image" />
                      <button class="btnNext" @click="nextImage(index)">›</button>
                    </div>
                    <!-- Se quiser, pode colocar uma imagem padrão quando não tiver fotos -->
                    <div v-else>
                      <img src="/caminho/para/imagem-padrao.png" alt="Sem foto" class="carousel-image" />
                    </div>
                    <h5>{{ anu.descricao}}</h5>
                    <h4>R$ {{ anu.preco }}</h4>
                    <button class="" @click="irParaAnuncioIndividual(anu)">Acessar produto</button>
                    <button v-if="nivel == 1" class="" @click.stop="excluirAnuncio(anu)">Excluir anuncio</button>
                </div>
            </div>
        </main>
</template>

<script>
import axios from 'axios';
//import MainView from './layouts/mainView.vue'

export default{
    name: 'BuscarAnuncios',
    props: {
        msg: String
    },
    data(){
      return {id:0, titulo:"", data:"", descricao:"", preco:"", catid:"", catusu:"", perguntas:[], mensagem:"", nivel:0, formOn:false,
      anuncios:[], fotos:[], currentPhotoIndices: {}}
    },
    created() {
        this.nivel = this.$route.query.nivel; 
        this.carregarDados();
    },
    methods:{
        carregarDados() {
          this.mensagem = "";
          axios.get("http://localhost:8080/apis/anuncio")
          .then(result => {
            this.anuncios = result.data;

            this.anuncios.forEach((anu) => {
              if (!Array.isArray(anu.fotos)) {
                this.$set(anu, 'fotos', []);  // cria array vazio se não tiver fotos
              }
              this.$set(this.currentPhotoIndices, anu.id, 0);
            });
          }).catch(error => {
            this.mensagem = "Anúncio não encontrado";
          });
        },
        prevImage(anuncioIndex) {
    const anu = this.anuncios[anuncioIndex];
    if (!anu.fotos || anu.fotos.length === 0) return;

    let currentIndex = this.currentPhotoIndices[anu.id] || 0;
    currentIndex = (currentIndex - 1 + anu.fotos.length) % anu.fotos.length;
    this.$set(this.currentPhotoIndices, anu.id, currentIndex);
  },
  nextImage(anuncioIndex) {
    const anu = this.anuncios[anuncioIndex];
    if (!anu.fotos || anu.fotos.length === 0) return;

    let currentIndex = this.currentPhotoIndices[anu.id] || 0;
    currentIndex = (currentIndex + 1) % anu.fotos.length;
    this.$set(this.currentPhotoIndices, anu.id, currentIndex);
  },
        buscarTitulo(){
            axios.get("http://localhost:8080/apis/anuncio/buscarTitulo", {
              params: {
                  titulo: this.titulo
              }
            })
            .then(result=>{
              this.anuncios = result.data;
              if(this.anuncios.length === 0){
                this.mensagem = "Anúncio não encontrado";
              } else {
                this.mensagem = "";
              }
            })
            .catch(error=>{
              this.mensagem = "Erro na busca. Tente novamente.";
            })
        },
        irParaAnuncioIndividual(anuncio) {
            this.$router.push({name: "AnuncioIndividual" , params: {id: anuncio.id}, query: { nivel: this.nivel }});
        },
        excluirAnuncio(anuncio){
            if(confirm("Deseja realmente excluir o anúncio: " + anuncio.titulo + "?")){
            axios.delete("http://localhost:8080/apis/anuncio/"+anuncio.id).then(result=>{
              this.mensagem="Anuncio excluido com sucesso";
              this.$router.push({name: "BuscarAnuncios" , params: {nivel: this.nivel}});
            }).catch(error=>{
                alert(error)
            })
          }
        },
        irParaMenu(){
          this.$router.push({name: "Menu" , query: { nivel: this.nivel }});
        },
        irParaMenuAdm(){
          this.$router.push({name: "MenuAdm" , query: { nivel: this.nivel }});
        }
    }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

body {
  background-color: #0b0b3b;
  color: #fff;
  padding-top: 80px;
  font-size: 14px;

}

.carousel {
  position: relative;
  width: 250px;
  height: 150px;
  margin: 0 auto 15px auto;
  overflow: hidden;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
}

.carousel-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  display: block;
}

.btnPrev, .btnNext {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0,0,0,0.3);
  color: white;
  border: none;
  font-size: 24px;
  padding: 4px 10px;
  cursor: pointer;
  border-radius: 50%;
  user-select: none;
  transition: background-color 0.3s ease;
}

.btnPrev:hover, .btnNext:hover {
  background-color: rgba(0,0,0,0.6);
}

.btnPrev {
  left: 5px;
}

.btnNext {
  right: 5px;
}

.header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px 40px;
  background-color: #0a084d;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
  box-shadow: 0 4px 6px rgba(0,0,0,0.3);
}

.titulo-principal {
  font-size: 28px;
  font-weight: bold;
  margin: 10px 0;
}

.texto-instrucao {
  font-size: 18px;
  margin-bottom: 20px;
}

.logo a {
  font-size: 28px;
  font-weight: bold;
  color: #53baff;
  text-decoration: none;
  transition: color 0.3s;
}

.logo a:hover {
  color: #79cdff;
}

.navbarra a {
  font-size: 18px;
  margin-left: 30px;
  color: #ffffff;
  text-decoration: none;
  position: relative;
  transition: color 0.3s;
}

.navbarra a::after {
  content: "";
  position: absolute;
  width: 0%;
  height: 2px;
  bottom: -5px;
  left: 0;
  background-color: #53baff;
  transition: 0.3s;
}

.navbarra a:hover {
  color: #53baff;
}

.navbarra a:hover::after {
  width: 100%;
}

.main-content {
  text-align: center;
  padding: 60px 20px;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.sub-title {
  margin: 10px 0;
  font-size: 18px; /* antes estava 10px, isso parece muito pequeno; ajuste conforme necessário */
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
}

.form-pesquisa {
  margin-top: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 12px;
  width: 100%;
}

.form-pesquisa input {
  padding: 14px 20px;
  font-size: 18px;
  border-radius: 10px;
  border: 1px solid #ccc;
  width: 400px;
  max-width: 90%;
  background-color: #fff;
  color: #000;
  box-shadow: 0 2px 6px rgba(0,0,0,0.15);
}


.form-pesquisa button {
  padding: 12px 20px;
  font-size: 16px;
  border-radius: 6px;
  border: none;
  background-color: #53baff;
  color: white;
  cursor: pointer;
  transition: 0.3s ease;
}

.form-pesquisa button:hover {
  background-color: #3fa4e0;
}

.divAnuncios {
  margin-top: 40px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 25px;
  width: 100%;
  max-width: 1200px;
}

.cards {
  background-color: #ffffff;
  color: #000;
  border-radius: 12px;
  padding: 20px;
  width: 260px;
  box-shadow: 0 8px 16px rgba(0,0,0,0.2);
  transition: transform 0.2s, box-shadow 0.3s;
  cursor: pointer;
}

.cards:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0,0,0,0.3);
}

.cards h3 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #0b0b3b;
}

.cards h5 {
  font-size: 14px;
  color: #333;
}

.cards button {
  margin-top: 15px;
  padding: 10px 16px;
  font-size: 15px;
  font-weight: 500;
  border: none;
  border-radius: 8px;
  background-color: #0b5ed7;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.cards button:hover {
  background-color: #084298;
  transform: scale(1.05);
}


</style>