import axios from "axios";


export default {
  namespaced: true,
  state: {
    user: {
      firstname: '',
      lastname: '',
      username: '',
      bio: ''
    },
    userQuizzes: []
  },
  actions: {
    async fetchUserData(context) {
      try {
        const response = await axios.get('api/user/')
        context.commit("setUserData", response.data)
        await context.dispatch('fetchQuizData')
        return response.data
      } catch (e) {
        return false
      }
    },
    async logout(context) {
      await localStorage.removeItem('token')
      axios.defaults.headers.common['Authorization'] = null
      await this.$router.push('/main')
    },
    async fetchQuizData(context) {
      try {
        const response = await axios.get('quiz/list')
        context.commit('setQuizzesData', response.data.quizzes)
      } catch (e) {
        console.log(e)
      }
    },
    async deleteQuiz(context, quizId) {
      try {
        await axios.delete(`quiz/${quizId}`)
        await context.dispatch('fetchQuizData')
      } catch (e) {

      }
    },
    async createQuiz(context, quizName) {
      try {
        await axios.post('quiz/', {name: quizName})
        await context.dispatch('fetchQuizData')
        return true
      } catch (e) {
        return false
      }
    },
    async visitQuiz(context, quizId) {
      await context.dispatch('quizEditorModule/fetchQuizData', quizId , {root: true})
      await this.$router.push(`quiz`)
    }
  },
  mutations: {
    setUserData(state, userData) {
      state.user = userData
    },
    setQuizzesData(state, quizzesData) {
      state.userQuizzes = quizzesData
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
    },
    quizList(state) {
      return state.userQuizzes
    }
  }
}
