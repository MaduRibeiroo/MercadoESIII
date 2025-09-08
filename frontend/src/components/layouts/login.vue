<template>
  <!-- Bootstrap core CSS -->
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css"
    rel="stylesheet"
  />

  <!-- MDB UI Kit CSS -->
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
    rel="stylesheet"
  />

  <!-- Font Awesome para ícones -->
  <link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    rel="stylesheet"
  />

  <div class="page gradient-bg">
    <section class="vh-100">
      <div class="container py-5 h-100">
        <div class="row d-flex align-items-center justify-content-center h-100">
          <div class="col-md-8 col-lg-7 col-xl-6 mb-4 mb-lg-0">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
              class="img-fluid"
              alt="Phone image"
            />
          </div>
          <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
              <form @submit.prevent="login">
                <div class="wrapper">
                  <div class="card-switch">
                      <label class="switch">
                        <input type="checkbox" class="toggle">
                        <span class="slider"></span>
                        <span class="card-side"></span>
                        <div class="flip-card__inner">
                            <div class="flip-card__front">
                              <div class="title">Log in</div>
                              <div class="flip-card__div" action="">
                                  <input class="flip-card__input" v-model="nome" placeholder="Nome" type="nome">
                                  <input class="flip-card__input" style="margin-top: 20px;" v-model="senha" placeholder="Senha" type="password">
                                  <button class="flip-card__btn" @click="login">Logar!</button>
                              </div>
                            </div>
                            <div class="flip-card__back">
                              <div class="title">Cadastrar</div>
                              <div class="flip-card__div" action="">
                                  <input class="flip-card__input" v-model="nome" placeholder="Nome" type="nome">
                                  <input class="flip-card__input" style="margin-top: 20px;" v-model="senha" placeholder="Senha" type="password">
                                  <button class="flip-card__btn" @click="cadastrarUsuario">Cadastrar!</button>
                              </div>
                            </div>
                        </div>
                      </label>
                  </div>   
                </div>
              </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {
      nome: '',
      senha: '',
      erro: ''
    }
  },
  methods: {
    login() {
      axios.get('http://localhost:8080/apis/usuario')
      .then(resp => {
        const usuarios = resp.data;
        const usuario = usuarios.find(u => u.nome === this.nome && u.senha === this.senha);

        if (usuario) {
          localStorage.setItem('usuarioLogado', JSON.stringify(usuario));

          if (usuario.nivel === 1) {
            this.$router.push({name: "MenuAdm", query: { nivel: usuario.nivel } });
          } else if (usuario.nivel === 2 || usuario.nivel === 3) {
            this.$router.push({name: "Menu" , query: { nivel: usuario.nivel } });
          } else {
            this.erro = 'Nível de acesso desconhecido.';
          }
        } else {
          this.erro = 'Usuário ou senha inválidos.';
        }
      })
      .catch(() => {
        this.erro = 'Erro ao conectar ao servidor.';
      });
    },

    cadastrarUsuario() {
      const novoUsuario = {
        nome: this.nome,
        senha: this.senha,
        nivel: 2  // Define o usuário como nível 2
      };

      axios.post('http://localhost:8080/apis/usuario', novoUsuario)
        .then(() => {
          this.mensagem = 'Usuário cadastrado com sucesso!';
          this.nome = '';
          this.senha = '';
        })
        .catch(() => {
          this.erro = 'Erro ao cadastrar usuário. Verifique o servidor.';
        });
    }

  }
}
</script>

<style scoped>
.gradient-bg {
  background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
}

.divider:after,
.divider:before {
content: "";
flex: 1;
height: 1px;
background: #eee;
}

.wrapper {
  margin-left: 150px;
  --input-focus: #ffffff;
  --font-color: #323232;
  --font-color-sub: #666;
  --bg-color: #fff;
  --bg-color-alt: #666;
  --main-color: #323232;
    /* display: flex; */
    /* flex-direction: column; */
    /* align-items: center; */
}
/* switch card */
.switch {
  transform: translateY(-200px);
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 30px;
  width: 50px;
  height: 20px;
}

.card-side::before {
  position: absolute;
  content: 'Log in';
  left: -70px;
  top: 0;
  width: 100px;
  text-decoration: underline;
  color: var(--font-color);
  font-weight: 600;
}

.card-side::after {
  position: absolute;
  content: 'Cadastrar';
  left: 70px;
  top: 0;
  width: 100px;
  text-decoration: none;
  color: var(--font-color);
  font-weight: 600;
}

.toggle {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  box-sizing: border-box;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: var(--bg-colorcolor);
  transition: 0.3s;
}

.slider:before {
  box-sizing: border-box;
  position: absolute;
  content: "";
  height: 20px;
  width: 20px;
  border: 2px solid var(--main-color);
  border-radius: 5px;
  left: -2px;
  bottom: 2px;
  background-color: var(--bg-color);
  box-shadow: 0 3px 0 var(--main-color);
  transition: 0.3s;
}

.toggle:checked + .slider {
  background-color: var(--input-focus);
}

.toggle:checked + .slider:before {
  transform: translateX(30px);
}

.toggle:checked ~ .card-side:before {
  text-decoration: none;
}

.toggle:checked ~ .card-side:after {
  text-decoration: underline;
}

/* card */ 

.flip-card__inner {
  width: 300px;
  height: 350px;
  position: relative;
  background-color: transparent;
  perspective: 1000px;
    /* width: 100%;
    height: 100%; */
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

.toggle:checked ~ .flip-card__inner {
  transform: rotateY(180deg);
}

.toggle:checked ~ .flip-card__front {
  box-shadow: none;
}

.flip-card__front, .flip-card__back {
  padding: 20px;
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  background: lightgrey;
  gap: 20px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
}

.flip-card__back {
  width: 100%;
  transform: rotateY(180deg);
}

.flip-card__form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.title {
  margin: 20px 0 20px 0;
  font-size: 25px;
  font-weight: 900;
  text-align: center;
  color: var(--main-color);
}

.flip-card__input {
  width: 250px;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 15px;
  font-weight: 600;
  color: var(--font-color);
  padding: 5px 10px;
  outline: none;
}

.flip-card__input::placeholder {
  color: var(--font-color-sub);
  opacity: 0.8;
}

.flip-card__input:focus {
  border: 2px solid var(--input-focus);
}

.flip-card__btn:active, .button-confirm:active {
  box-shadow: 0px 0px var(--main-color);
  transform: translate(3px, 3px);
}

.flip-card__btn {
  margin: 20px 0 20px 0;
  width: 120px;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 17px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
} 
</style>

