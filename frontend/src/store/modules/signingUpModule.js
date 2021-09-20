import axios from "axios";

export default {
  namespaced: true,
  state: {},
  actions: {
    async resisterUser(context, userData) {
      try {
        console.log(userData)
        const response = await axios.post(`http://localhost:8081/api/auth/signup`, userData)
        return true
      } catch (e) {
        return false
      }
    }
  },
  mutations: {},
  getters: {}
}
