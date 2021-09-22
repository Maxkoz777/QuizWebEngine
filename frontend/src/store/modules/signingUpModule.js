import axios from "axios";

export default {
  namespaced: true,
  state: {},
  actions: {
    async resisterUser(context, userData) {
      try {
        const response = await axios.post(`api/auth/signup`, userData)
        console.log(response)
        return true
      } catch (e) {
        return e
      }
    }
  },
  mutations: {},
  getters: {}
}
