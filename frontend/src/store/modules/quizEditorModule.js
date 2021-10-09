import axios from "axios";

export default {
  namespaced: true,
  state: {
    quiz: {
      currentQuizId: null,
      questions: [
        // {
        //   "question": "What is i-node?",
        //   id: '1',
        //   "answer": [
        //     {
        //       "answerText": "1",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "2",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "3",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "4",
        //       "isRight": true
        //     }
        //   ]
        // },
        // {
        //   "question": "What is j-node?",
        //   id: '2',
        //   "answer": [
        //     {
        //       "answerText": "ddsdasdadsdassss1",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "2",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "3",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "4",
        //       "isRight": true
        //     }
        //   ]
        // },
        // {
        //   "question": "What is j-node?",
        //   id: '3',
        //   "answer": [
        //     {
        //       "answerText": "ddsdasdadsdassss1",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "2",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "3",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "4",
        //       "isRight": true
        //     }
        //   ]
        // },
        // {
        //   "question": "What is j-node?",
        //   id: '4',
        //   "answer": [
        //     {
        //       "answerText": "ddsdasdadsdassss1",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "2",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "3",
        //       "isRight": false
        //     },
        //     {
        //       "answerText": "4",
        //       "isRight": true
        //     }
        //   ]
        // }
      ]
    },
    question: {
      currentQuestion: null
    }
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
      const question = await context.dispatch('fetchQuestion', questionId)
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
