import axios from "axios";

export default {
  namespaced: true,
  state: {
    playerName: "",
    quiz: {
      questions: []
    },
    currentQuestion: {},
    questionNumber: 0,
    score: 0,
    isFinished: true
  },
  actions: {
    async enterDailyQuiz(context){

    },
    async validateQuizPin(context, quizId) {
      try {
        const response = await axios.get(`quiz/${quizId}`);
        const questionList = [];
        for (let question in response.data.questions) {
          let questionData = await context.dispatch(
            "fetchQuestion",
            response.data.questions[question].questionId
          );
          questionList.push(questionData);
        }
        context.commit("setQuestions", questionList);
        return true;
      } catch (e) {
        return false;
      }
    },
    async fetchQuestion(context, questionId) {
      const question = await axios.get(`question/${questionId}`);
      return question.data;
    },
    async setName(context, name) {
      await context.commit("setPlayerName", name);
      await context.dispatch("startQuiz");
    },
    async startQuiz(context) {
      context.commit("setCurrentQuestion", context.getters.questions[0]);
    },
    async incrementQuestionNumber(context) {
      context.commit("incrementQuestionNumber");
      const question =
        context.getters.questions[context.getters.currentQuestionNumber];
      question
        ? await context.commit("setCurrentQuestion", question)
        : context.commit("setIsFinished", true);
    },
    async validateAnswer(context, answerId) {
      const correctAnswerData = await axios.get(
        `question/${context.getters.currentQuestion.questionId}/correctAnswer`
      );
      const correctAnswerId = correctAnswerData.data.answerId;
      if (answerId === correctAnswerId) {
        context.commit("updateScore", 1);
        return true;
      } else {
        return false;
      }
    },
    async removeQuizData(context) {
      await context.commit("setPlayerName", null);
      await context.commit("setQuestions", []);
      await context.commit("setCurrentQuestion", {});
      await context.commit("dropCurrentQuestionNumber");
      await context.commit("dropScore");
      await context.commit("setIsFinished", false);
    }
  },
  mutations: {
    setQuestions(state, questions) {
      state.quiz.questions = questions;
    },
    setPlayerName(state, name) {
      state.playerName = name;
    },
    setCurrentQuestion(state, question) {
      state.currentQuestion = question;
    },
    incrementQuestionNumber(state) {
      state.questionNumber++;
    },
    dropCurrentQuestionNumber(state) {
      state.questionNumber = 0;
    },
    updateScore(state, point) {
      state.score = state.score + point;
    },
    dropScore(state) {
      state.score = 0;
    },
    setIsFinished(state, val) {
      state.isFinished = val;
    }
  },
  getters: {
    questions(state) {
      return state.quiz.questions;
    },
    playerName(state) {
      return state.playerName;
    },
    currentQuestion(state) {
      return state.currentQuestion;
    },
    currentQuestionNumber(state) {
      return state.questionNumber;
    },
    score(state) {
      return state.score;
    },
    isFinished(state) {
      return state.isFinished;
    },
    questionsLength(state) {
      return state.quiz.questions.length;
    }
  }
};
