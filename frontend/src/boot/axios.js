import Vue from 'vue'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8081/'
axios.defaults.headers.common['Authorization'] ='Bearer' + localStorage.getItem('token')
Vue.prototype.$http = axios;
const token = localStorage.getItem('token')

if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}
