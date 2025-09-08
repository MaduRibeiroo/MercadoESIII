<template>
    <body>
        <header class="header">
            <a href="#" class="logo">Mercado FIPP</a>
    
            <nav class="navbarra">
              <a style="--i:2"><router-link to="/">USUARIO</router-link></a>
                <!--<a href="#id-home" style="--i:1" class="active">Home</a>
                <a href="#id-sobre" style="--i:2">Sobre</a>
                <a href="#id-relatorio" style="--i:3">Relatório</a>
                <a href="#id-contato" style="--i:4">Contato</a>-->
            </nav>
        </header>
        <section class="home" id="id-home">
        <div class="home-conteudo">
            <h3>Bem vindo ao</h3>
            <h1>Mercado FIPP</h1>
            <h3>Somos uma empresa especializadas em <span style="color: #53bafff7;">Vendas</span></h3>
            <p>O Mercado FIPP é a plataforma ideal para comprar, vender e interagir de forma prática e segura.</p>
        </div>
        <span class="home-imgHover"></span>
        </section>
    
        <section style="margin-top: -150px;" class="sobre" id="id-sobre">
            <div class="sobre-img">
                <img src="../../assets/lobo.png">
            </div>
            <div class="sobre-texto">
                <h2>Sobre o <span>Mercado FIPP</span></h2>
                <p>Aqui, você pode criar seu perfil, publicar anúncios em diversas categorias, adicionar fotos dos seus produtos e tirar dúvidas diretamente com os vendedores por meio de perguntas e respostas nos anúncios. Nosso objetivo é facilitar a negociação entre os usuários, promovendo um ambiente colaborativo e eficiente. Explore as categorias, publique seu anúncio e aproveite tudo que o Mercado FIPP tem a oferecer!</p>
                <p style="margin-top: -30px;">Deseja verificar quais as categorias existentes?</p>
                <button class="btn-box" @click="irParaCategoria">CATEGORIA</button>
            </div>
        </section>
    
        <section>
            <div class="relatorio" id="id-relatorio">
                <div class="container">
                    <h1 class="sub-title"><span>Anuncios e Perguntas</span></h1>
                    <div class="relatorio-lista">
                        <div>
                            <h2>Criar anuncio</h2>
                            <p>Tem algo para vender ou divulgar? No Mercado FIPP, anunciar é rápido e fácil!
                              Basta escolher a categoria ideal, preencher as informações do seu produto ou serviço, adicionar fotos e publicar. Quanto mais detalhado for seu anúncio, maiores as chances de atrair compradores interessados</p>
                              <button class="btn-box" style="margin-top: 10px;" @click="irParaFormAnuncio">CRIAR</button>
                              <router-view></router-view>
                        </div>
                        <div>
                            <h2>Buscar anuncio e perguntar</h2>
                            <p>No processo de tratamento de água, a temperatura é um fator crítico. Ela afeta a eficiência das reações químicas e biológicas, como a coagulação, sedimentação e desinfecção. Águas mais frias tendem a retardar esses processos, enquanto águas mais quentes aceleram as reações.</p>
                            <button class="btn-box" style="margin-top: 10px;" @click="irParaBuscaAnuncios">BUSCAR</button>
                        </div>
                    </div>
                </div>
            </div>
          </section>
    
    </body>
  <!--<div id="menu">
    <div><router-link to="/form-categorias/Categorias">Categoria</router-link></div>
    <div><router-link to="/form-usuario/Usuário">Usuário</router-link></div>
  </div>
   <router-view></router-view>-->
</template>

<script>
import FormAnuncio from '../forms/FormAnuncio.vue';
import FormCategoria from '../forms/FormCategoria.vue';
import FormUsuario from '../forms/FormUsuario.vue';
import buscarAnuncios from './buscarAnuncios.vue';

export default {
  name: 'Menu',
  data(){
    return{nivel:0}
  },
  components: {
    FormCategoria, FormUsuario, FormAnuncio, buscarAnuncios
  },
  created(){
    this.nivel = this.$route.query.nivel;  
  },
  methods: {
    irParaFormAnuncio() {
      this.$router.push({name: "FormAnuncios" , params: { msg: "algumaMensagem" }, query: { nivel: this.nivel }});
    },
    irParaCategoria() {
      this.$router.push('/view-categoria');
    },
    irParaBuscaAnuncios(){
        this.$router.push({name: "BuscarAnuncios" , query: { nivel: this.nivel }});
    }
  }
}
</script>

<style>

*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'poppins', sans-serif;}

body{
    color: white;
    background-color: #030037;}

.header{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    padding: 20px 10px;
    background-color: #000143;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 100;}

.logo{
    position: relative;
    font-size: 25px;
    color: white;
    text-decoration: none;
    font-weight: 600;
    cursor: default;
    opacity: 0;
    animation: slideRight 1s ease forwards;}

.navbarra a{
    display: inline-block;
    font-size: 25px;
    color: white;
    text-decoration: none;
    font-weight: 500;
    margin-left: 35px;
    transition: slideTop .5s ease forwards;
    opacity: 0;
    animation: slideLeft 1s ease forwards;
    animation-delay: calc(.2s * var(--i))}

.navbarra a:houver{
    color: #53bafff7;}

.contato{
    position: relative;
    justify-content: center;
    background-color: #030037;
    background-position: center;
    display: flex;
    align-items: center;
    padding: 70px 10% 0;
}

.subtitulo {
    text-align: center;
    padding-top: 40px;
    font-weight: 600;
    font-size: 48px;
    color: #ffffff;
    text-shadow: 3px 3px 3px #132c89;
}

.texto2 {
    font-weight: 300;
    font-size: 20px;
    color: #ffffff;
    text-align: center;
    text-shadow: 3px 3px 3px #132c89;
}

.home{
    position: relative;
    width: 100%;
    justify-content: space-between;
    height: 100vh;
    background-color: #53bafff7;
    background: url(/src/assets/fundo.png), no-repeat;
    background-size: cover;
    background-position: center;
    display: flex;
    align-items: center;
    padding: 70px 10% 0;
    margin-top: -100px;
}

.home-conteudo{
    max-width: 600px;}

.home-conteudo h3{
    font-size: 32px;
    font-weight: 700;
    opacity: 0;
    animation: slideBottom 1s ease forwards;
    animation-delay: .7s;}

.home-conteudo h3:nth-of-type(2){
    margin-bottom: 30px;
    animation: slideTop 1s ease forwards;
    animation-delay: .7s;}

.home-conteudo h3 span{
    color: #53bafff7;}

.home-conteudo h1{
    font-size: 56px;
    font-weight: 700;
    margin: -3px 0;
    opacity: 0;
    animation: slideRight 1s ease forwards;
    animation-delay: 1s;}

.home-conteudo p{
    font-size: 20;
    opacity: 0;
    animation: slideLeft 1s ease forwards;
    animation-delay: 1s;}

.home-social a{
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    background-color: transparent;
    border: 2px solid #53bafff7;
    border-radius: 50%;
    font-size: 20px;
    color: #53bafff7;
    text-decoration: none;
    transition: .5s ease;
    opacity: 0;
    animation: slideLeft 1s ease forwards;
    animation-delay: calc(.2s * var(--i));
    margin: 30px 15px 30px 0;}

.home-social a:hover{
    background-color: #53bafff7;
    color: #000000;
    box-shadow: 0 0 20px #53bafff7;}

.btn-box{
    display: inline-block;
    padding: 12px 28px;
    background-color: #53bafff7;
    border-radius: 40px;
    font-size: 16px;
    color: #000;
    letter-spacing: 1px;
    text-decoration: none;
    font-weight: 600;
    opacity: 0;
    animation: slideTop 1s ease forwards;
    animation-delay: 2s;
    box-shadow: 0 0 5px #53bafff7, 0 0 25px #53bafff7;}

.sobre{
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    align-items: center;
    gap: 1.5rem;
    background-color: #030037;}

.sobre-img img{
    padding-bottom: 20%;
    max-width: 630px;
    height: auto;
    width: 100%;
    border-radius: 80vh;
    margin-top: 15%;}

.sobre-texto h2{
    font-size: 60px;}

.sobre-texto h2 span{
    color: #53bafff7;}

.sobre h4{
    font-size: 29px;
    font-weight: 600;
    color: white;
    line-height: 1.7;
    margin: 15px 0 30px;
}

.sobre-texto p{
    color: white;
    font-size: 20px;
    line-height: 1.4;
    margin-bottom: 4rem;
}

.relatorio{
    color: aliceblue;
    font-size: 20px;
    line-height: 1.4;
    margin-bottom: 4rem;
    background-color: #030037;}

.sub-title{
    text-align: center;
    font-size: 60px;
    padding-bottom: 70px;}

.sub-title span{
    color: #53bafff7;}

.container{
    padding: 90px;}

.relatorio-lista{
    display: grid;
    grid-template-columns: repeat(auto-fit,minmax(259px,1fr));
    grid-gap: 40px;
    margin-top: 50px;}

.relatorio-lista div{
    background-color: transparent;
    padding: 40px;
    font-size: 13px;
    font-weight: 13px;
    border-right: 10px;
    border-radius: 20px;
    transition: background 0.5s, transform 0.5;
    box-shadow: 1px 1px 20px #170251,
                1px 1px 40px #0d0ab6;
}

.relatorio-lista div i{
    font-size: 50px;
    margin-bottom: 30px;}

.relatorio-lista div h2{
    font-size: 30px;
    font-weight: 500;
    margin-bottom: 15px;}

.relatorio-lista div a{
    text-decoration: none;
    color: black;
    font-size: 12px;
    margin-top: 20px;
    display: inline-block;}

.relatorio-lista div p{
    font-size: 14px;
}

.read{
    display: inline-block;
    padding: 12px 28px;
    background: #53bafff7;
    border-radius: 40px;
    font-size: 16px;
    color: black;
    letter-spacing: 1px;
    text-decoration: none;
    font-weight: 600;
    opacity: 0;
    animation: slideTop 1s ease forwards;
    animation-delay: 2s;
    box-shadow: 0 0 5px #53bafff7,
    0 0 25px #53bafff7;}

.read:hover{
    box-shadow: 0 0 5px #53bafff7, 0 0 25px #53bafff7, 0 0 50px #53bafff7, 0 0 100px #53bafff7, 0 0 200px #53bafff7;}

.relatorio-lista div:hover{
    transform: translate(-10px);}

.tabela{
    width: 80%; /* Define a largura da tabela */
    margin: 20px auto; /* Centraliza a tabela */
    border-collapse: collapse; /* Remove espaços entre as bordas */
    background-color: white;
    box-shadow: 0px 4px 15px 2px rgba(174, 174, 240, 0.3);
    margin-top: 100px;
    color: #000;
}

.tabela th, .tabela td {
    padding: 10px;
    border: 1px solid #ddd; /* Adiciona bordas às células */
    text-align: center;

}


@keyframes slideRight{
    0%{
        transform: translateX(-100px);
        opacity: 0;
    }
    100%{
        transform: translateX(0px);
        opacity: 1;
    }
}
@keyframes slideLeft{
    0%{
        transform: translateX(100px);
        opacity: 0;
    }
    100%{
        transform: translateX(0px);
        opacity: 1;
    }
}


@keyframes slideTop{
    0%{
        transform: translateY(100px);
        opacity: 0;
    }
    100%{
        transform: translateY(0px);
        opacity: 1;
    }
}
@keyframes slideBottom{
    0%{
        transform: translateY(-100px);
        opacity: 0;
    }
    100%{
        transform: translateY(0px);
        opacity: 1;
    }
}




</style>
