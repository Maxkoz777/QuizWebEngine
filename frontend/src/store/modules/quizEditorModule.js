import axios from "axios";

export default {
  namespaced: true,
  state: {
    quiz: {
      currentQuizId: null,
      questions: []
    },
    question: {
      currentQuestion: null
    },
    isEditMode: false,
  },
  actions: {
    async fetchQuizData(context, quizId) {
      const response = await axios.get(`quiz/${quizId}`)

      const questionList = []
      const questionIds = response.data.questions

      for (let questionId of questionIds) {
        const question = await context.dispatch('fetchQuestion', questionId.questionId)
        questionList.push(question)
      }
      context.commit('setCurrentQuizId', quizId)
      context.commit('setQuestions', questionList)
    },

    async fetchQuestion(context, questionId) {
      const question = await axios.get(`question/${questionId}`)
      return question.data
    },
    async chooseQuestion(context, questionId) {
      const question =questionId? await context.dispatch('fetchQuestion', questionId):''
      context.commit('setCurrentQuestion', question)
    },
    async createQuestion(context) {
      const newQuestion = await axios.post(`question/${context.getters.quizId}`, {
        "question": "Empty question",
        "answer": [
          {
            "answerText": "answer 1",
            "isRight": false
          },
          {
            "answerText": "answer 2",
            "isRight": false
          },
          {
            "answerText": "answer 3",
            "isRight": false
          },
          {
            "answerText": "answer 4",
            "isRight": true
          }
        ]
      })
      await context.dispatch('fetchQuizData', context.getters.quizId)
      await context.dispatch('chooseQuestion', newQuestion.data.questionId)
    },
    setEditMode(context, val) {
      context.commit('setEditMode', val)
    },
    async updateQuestions(context, newQuestionsData) {
      await axios.put(`question/${context.getters.currentQuestion.questionId}`, newQuestionsData)
      await context.dispatch('fetchQuizData', context.getters.quizId)
      await context.dispatch('chooseQuestion', context.getters.currentQuestion.questionId)
    },
    async deleteQuestion(context) {
      await axios.delete(`question/${context.getters.currentQuestion.questionId}`)
      await context.dispatch('fetchQuizData', context.getters.quizId)
      await context.dispatch('chooseQuestion', null)
      console.log('delete')
    }
  },
  mutations: {
    setQuestions(state, questionsIds) {
      state.quiz.questions = questionsIds
    },
    setCurrentQuizId(state, quizId) {
      state.quiz.currentQuizId = quizId
    },
    setCurrentQuestion(state, question) {
      state.question.currentQuestion = question
    },
    setEditMode(state, val) {
      state.isEditMode = val
    }
  },
  getters: {
    questions(state) {
      return state.quiz.questions
    },
    currentQuestion(state) {
      return state.question.currentQuestion
    },
    quizId(state) {
      return state.quiz.currentQuizId
    },
    editMode(state) {
      return state.isEditMode
    },
  }
}
