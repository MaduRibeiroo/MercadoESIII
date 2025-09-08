<template>
    <header class="header">
            <a v-if="nivel == 2" href="#" class="logo"><router-link to="/Menu">Mercado FIPP</router-link></a>
            <a v-if="nivel == 1" href="#" class="logo"><router-link to="/view-adm">Mercado FIPP</router-link></a>
            <nav class="navbarra">
              <a style="--i:1"><router-link to="/view-categoria">Categoria</router-link></a>
              <a v-if="nivel==1" style="--i:2"><router-link to="/form-usuario/Usuário">Admin</router-link></a>
              <a v-if="nivel==2" style="--i:2"><router-link to="/form-usuario/Usuário">Usuario</router-link></a>
            </nav>
    </header>
    <div class="mainContent">
            <div class="top-buttons">
                <button v-if="nivel == 2" class="btn-box" @click="alterarAnuncio">Alterar Anuncio</button>
                <button v-if="nivel == 1" class="btn-box" @click="excluirAnuncio">Excluir Anuncio</button>
            </div>
            <div v-if="anuncios && anuncios.titulo" class="infos">
                    <h3>{{anuncios.titulo}}</h3>
                    <p>R$ {{ anuncios.preco }}</p>
                    <h6>Descrição do produto</h6>
                    <p>{{ anuncios.descricao}}</p>
            </div>
        <div class="interacao">
            <form class="form-pesquisa" @submit.prevent="adicionarPergunta">
                    <p>Envie a sua pergunta aqui</p>
                    <input type="text" id="buscar" placeholder="Digite sua pergunta..." v-model="texto">
                    <button id="botaoPesquisar" type="submit">Enviar pergunta</button>
            </form>
            <div class="perguntasRespostas" v-if="anuncios && anuncios.perguntas && anuncios.perguntas.length">
                <div v-for="(per, index) in this.anuncios.perguntas" :key="index" class="pergunta">
                    <h3>{{ per.texto }}</h3>
                    <p v-if="!per.resposta" @click="mostrarInput(index)" style="cursor: pointer; color: blue;">Responder</p>
                    <div v-if="respostaVisivel[index] && !per.resposta">
                    <input
                        type="text"
                        v-model="respostas[index]"
                        placeholder="Digite a resposta..."
                    />
                    <button @click="adicionarResposta(index)">Enviar</button>
                    </div>
                    <div v-if="per.resposta" class="resposta-bloco">
                        <p class="resposta-label">Resposta</p>
                        <h3 class="resposta-texto">{{ per.resposta }}</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default{
    name: 'AnuncioIndividual',
    props:{
        msg: String
    },
    data(){
        return {id:0, titulo:"", data:"", descricao:"", preco:"", catid:"", catusu:"", perguntas:[],respostas: [], respostaVisivel:[], mensagem:"", nivel:0, formOn:false,
        anuncios: null}
    },
    created() {
        this.id = this.$route.params.id;  
        this.nivel = this.$route.query.nivel;
        this.carregarDados();
    },
    methods:{
        carregarDados(){
            axios.get("http://localhost:8080/apis/anuncio/"+this.id)
            .then(result=>{
                this.anuncios=result.data;
                this.respostaVisivel = Array.isArray(this.anuncios.perguntas)
                    ? this.anuncios.perguntas.map(() => false)
                    : [];

                this.respostas = Array.isArray(this.anuncios.perguntas)
                    ? this.anuncios.perguntas.map(() => "")
                    : [];
            }).catch(error=>{
                alert(error)
            })
        },
        adicionarPergunta(){
            axios.get("http://localhost:8080/apis/anuncio/add-pergunta/"+this.anuncios.id+"/"+this.texto)
            .then(result=>{
                this.carregarDados();
                this.texto = "";
            }).catch(error=>{
                alert(error)
            })
        },
        adicionarResposta(index){
            axios.get("http://localhost:8080/apis/pergunta/add-resposta", {
            params: {
                id: this.anuncios.perguntas[index].id,
                texto: this.respostas[index]
            }
        }).then(result=>{
                this.carregarDados();
            }).catch(error=>{
                alert(error)
            })
        },
        mostrarInput(index) {
            this.respostaVisivel[index] = true;
        },
        excluirAnuncio(){
            if(confirm("Deseja realmente excluir o anúncio: " + this.anuncios.titulo + "?")){
            axios.delete("http://localhost:8080/apis/anuncio/"+this.id).then(result=>{
              this.mensagem="Anuncio excluido com sucesso";
              this.$router.push("/buscarAnuncios");
            }).catch(error=>{
                alert(error)
            })
          }
        },
        alterarAnuncio(){
          if(confirm("Deseja realmente alterar o anúncio: " + this.anuncios.titulo + "?")){
            this.$router.push({name: "FormAnuncios" , params: { msg: "algumaMensagem" }, query: { nivel: this.nivel }});
          }
        }
    }
}
</script>

<style>
.header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px;
  background-color: #000143;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
}

.logo {
  font-size: 25px;
  color: white;
  text-decoration: none;
  font-weight: 600;
  animation: slideRight 1s ease forwards;
}

.mainContent {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 100px 20px 40px 20px;
  /* era 120px, agora 100px */
  position: relative;
}

.top-buttons {
  position: absolute;
  top: 10px;
  /* Adicionado espaço no topo */
  right: 20px;
  /* Botões afastados da direita */
  display: flex;
  gap: 15px;
  /* Espaço entre os botões */
}

.btn-box {
  display: inline-block;
  padding: 12px 28px;
  background-color: #4c9bcf;
  border-radius: 40px;
  font-size: 16px;
  color: #000;
  letter-spacing: 1px;
  text-decoration: none;
  font-weight: 600;
  opacity: 0;
  animation: slideLeft 1s ease forwards;
  animation-delay: 1s;
  box-shadow: none;
  /* Removido brilho azul */
}

/* Quadro do anúncio */
.infos,
.interacao {
  background-color: white;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 95%;
  /* aumentou de 90% para 95% */
  max-width: 1000px;
  /* aumentou de 800px para 1000px */
  margin-top: 20px;
  color: black;
}

.form-pesquisa {
  display: flex;
  flex-direction: column;
  /* Agora empilha texto + input/botão */
  align-items: flex-start;
  gap: 10px;
  margin-bottom: 20px;
  /* Separação das perguntas abaixo */
}

.form-pesquisa input,
.form-pesquisa button {
  margin-top: 5px;
}

#buscar {
  flex: 1;
  /* faz o input ocupar o maior espaço possível */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  max-width: 600px;
  /* limita largura para evitar ficar grande demais */
}

.pergunta h3:last-of-type {
  margin-left: 30px;
  /* Indenta a resposta */
  font-weight: normal;
  color: #333;
}

#botaoPesquisar {
  padding: 10px;
  background-color: #000143;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s;
}

#botaoPesquisar:hover {
  background-color: #1a1a70;
}

.pergunta {
  margin-bottom: 30px;
  /* Aumenta o espaço entre grupos de pergunta/resposta */
  padding-bottom: 15px;
  border-bottom: 1px solid #e0e0e0;
  /* Linha para separar visualmente */
}

.resposta-bloco {
  margin-top: 8px;
  margin-left: 30px;
}

.resposta-label {
  font-size: 12px;
  color: #aaa;
  margin-bottom: 2px;
}

.resposta-texto {
  font-weight: normal;
  color: #333;
  margin: 0;
}

.infos h3 {
  font-size: 20px;
  margin-bottom: 10px;
}

.infos p {
  font-size: 18px;
  margin-bottom: 15px;
}

.infos h6 {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #555;
}
</style>