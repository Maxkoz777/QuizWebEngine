import axios from "axios";

export default {
  namespaced: true,
  state: {
    user: {
      firstname: '',
      lastname: '',
      username: '',
      bio: ''
    }
  },
  actions: {
    async fetchUserData(context) {
      try {
        const response = await axios.get('api/user/')
        context.commit("setUserData", response.data)
        return response.data
      } catch (e) {
        return false
      }
    },
    async logout(context) {
      await localStorage.removeItem('token')
      await this.$router.push('/main')
    }
  },
  mutations: {
    setUserData(state, userData) {
      state.user = userData
    }
  },
  getters: {
    userData(state) {
      return state.user
    },
    username(state) {
      return state.user.username
    },
    firstname(state) {
      return state.user.firstname
    },
    lastname(state) {
      return state.user.lastname
    }
  }
}
