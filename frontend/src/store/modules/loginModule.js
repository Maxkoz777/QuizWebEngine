import axios from "axios";

export default {
  namespaced: true,
  state: {},
  actions: {
    async login(context, userData) {
      try {
        const response = await axios.post(`api/auth/signin`, userData);
        await localStorage.setItem("token", response.data.token);
        axios.defaults.headers.common["Authorization"] = response.data.token;
        await this.$router.push("/");
        return true;
      } catch (e) {
        return false;
      }
    }
  },
  mutations: {},
  getters: {}
};
