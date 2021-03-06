import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import { URL } from './gobal'
import { URL2 } from './gobal'
import './assets/css/icon.css'
// import axios from 'axios'
// Vue.prototype.$axios = axios


const app = createApp(App)
app.config.globalProperties.$URL=URL
installElementPlus(app)
app
    .use(store)
    .use(router)
    .mount('#app')