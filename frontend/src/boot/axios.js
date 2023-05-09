import Vue from 'vue'
import axios from 'axios'


// axios.defaults.baseURL = 'http://localhost:8080/'
axios.defaults.baseURL  = '192.168.31.230:8080'
console.log(process.env)
console.log('back url:',  process.env.BACK_URL)


axios.defaults.headers.common['Authorization'] ='Bearer' + localStorage.getItem('token')
Vue.prototype.$http = axios;
const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

