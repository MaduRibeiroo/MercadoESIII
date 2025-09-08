import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

import App from './App.vue'
import FormCategoria from "./components/forms/FormCategoria.vue"
import FormUsuario from "./components/forms/FormUsuario.vue"
import FormAnuncio from './components/forms/FormAnuncio.vue'
import MainView from './components/layouts/mainView.vue'
import ViewADM from './components/layouts/viewADM.vue'
import login from './components/layouts/login.vue'
import ViewCat from './components/layouts/viewCat.vue'
import buscarAnuncios from './components/layouts/buscarAnuncios.vue'
import AnuncioIndividual from './components/layouts/AnuncioIndividual.vue'
import viewCatADM from './components/layouts/viewCatADM.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
    { path: '/', component: login, props: true},
    { path: '/Menu', name: 'Menu', component: MainView, props: true},
    { path: '/view-adm', name: 'MenuAdm', component: ViewADM, props: true},
    { path: '/view-categoria-adm', component: viewCatADM, props: true},
    { path: '/view-categoria', component: ViewCat, props: true},
    { path: '/form-categorias/:msg', component: FormCategoria, props: true },
    { path: '/form-usuario/:msg',  component: FormUsuario, props: true  },
    { path: '/form-anuncio/:msg', name: 'FormAnuncios', component: FormAnuncio, props: true },
    { path: '/buscarAnuncios', name: 'BuscarAnuncios', component: buscarAnuncios, props: true},
    { path: '/anuncioIndividual/:id', name: 'AnuncioIndividual', component: AnuncioIndividual, props: true}
    ]
});
    
const app=createApp(App);
app.use(router)
app.mount('#app')
