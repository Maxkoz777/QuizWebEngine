import axios from "axios";

export default {
  namespaced: true,
  state: {
    quiz: {
      currentQuizId: null,
      questions: [
        {
          "question": "What is i-node?",
          id: '1',
          "answer": [
            {
              "answerText": "1",
              "isRight": false
            },
            {
              "answerText": "2",
              "isRight": false
            },
            {
              "answerText": "3",
              "isRight": false
            },
            {
              "answerText": "4",
              "isRight": true
            }
          ]
        },
        {
          "question": "What is j-node?",
          id: '2',
          "answer": [
            {
              "answerText": "ddsdasdadsdassss1",
              "isRight": false
            },
            {
              "answerText": "2",
              "isRight": false
            },
            {
              "answerText": "3",
              "isRight": false
            },
            {
              "answerText": "4",
              "isRight": true
            }
          ]
        }
      ]
    },
    question: {
      currentQuestion: null
    }
  },
  actions: {
    async fetchQuizData(context, quizId) {
      const response = await axios.get(`quiz/${quizId}`)
      context.commit('setCurrentQuizId', quizId)
      context.commit('setQuestions', response.data.questions)
    },
    chooseQuestion(context, question) {
      context.commit('setCurrentQuestion', question)
    }
  },
  mutations: {
    setQuestions(state, questions) {
      state.quiz.questions = questions
    },
    setCurrentQuizId(state, quizId) {
      state.quiz.currentQuizId = quizId
    },
    setCurrentQuestion(state, question) {
      state.question.currentQuestion = question
    },
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
    }
  }
}
