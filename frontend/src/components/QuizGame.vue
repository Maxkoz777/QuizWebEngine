<template>
  <div
    class="full-height full-width question-container"
    v-if="currentQuestion && !isFinished"
  >
    <div class="fixed-top-right">
      <div class="q-pa-sm shadow-1 q-ma-sm user-name">
        {{ playerName }}
      </div>
    </div>
    <div class="fixed-bottom-left q-pa-lg">
      <div
        class="q-ma-sm timer row justify-center items-center q-pa-md"
        :class="{ timeRedClass: timerRed }"
      >
        {{ timerCount }}
      </div>
    </div>

    <div class="row items-center text-center justify-center">
      <div class="bg-white question-name shadow-2 q-pa-md text-h6">
        {{ currentQuestion.question }}
      </div>
    </div>
    <div class="row items-center justify-center q-gutter-lg">
      <div
        v-for="answer in currentQuestion.answer"
        :key="answer.answerId"
        class="answer-item text-center shadow-2  q-pa-lg"
        @click="validateNotify(answer.answerId)"
      >
        {{ answer.answerText }}
      </div>
    </div>
  </div>
  <div v-else-if="!currentQuestion" class="q-pa-lg text-h6 text-white">
    Ups.. it's seems that this session was closed.<br />
    Please reload the page and visit quiz again on main page<br />
    <q-btn @click="visitMainPage" class="q-mt-md">
      Go to main page
    </q-btn>
  </div>
  <div
    v-else-if="isFinished"
    class="row justify-center items-center fullscreen text-white"
  >
    <div class="text-center">
      <div class="text-h4">The quiz is finished !</div>
      <div class="text-h6 q-mt-md">
        Your score is: {{ score }} out of {{ questionsLength }}
      </div>
      <div>
        <q-btn
          flat
          class="accept-button q-mt-md"
          @click="visitMainPage"
          no-caps
        >
          Back to main menu
        </q-btn>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "QuizGame",
  data() {
    return {
      timerCount: 10,
      timerRed: false,
      timeout: ""
    };
  },
  watch: {
    timerCount: {
      handler(value) {
        if (this.currentQuestion && !this.isFinished) {
          if (value > 0) {
            this.setAnswerTimeout();
            if (value < 6) {
              this.timerRed = true;
            }
          } else {
            this.validateAnswer(null);
            this.timeUp();
          }
        }
      },
      immediate: true
    }
  },
  computed: {
    ...mapGetters({
      playerName: "gameModule/playerName",
      currentQuestion: "gameModule/currentQuestion",
      isFinished: "gameModule/isFinished",
      score: "gameModule/score",
      questionsLength: "gameModule/questionsLength"
    })
  },
  methods: {
    ...mapActions({
      validateAnswer: "gameModule/validateAnswer",
      incrementQuestionNumber: "gameModule/incrementQuestionNumber"
    }),
    async validateNotify(answerId) {
      let response = await this.validateAnswer(answerId);
      if (response) {
        this.rightAnswer();
      } else {
        this.wrongAnswer();
      }
      await this.incrementQuestionNumber();
      this.stopAnswerTimeout();
      this.timerCount = 10;
    },
    visitMainPage() {
      this.$router.push("/main");
    },
    //......... answer val notifications ..........//
    rightAnswer() {
      this.$q.notify({
        message: "You answered right!",
        color: "green"
      });
    },
    wrongAnswer() {
      this.$q.notify({
        message: "You answered wrong!",
        color: "red"
      });
    },
    timeUp() {
      this.$q.notify({
        message: "The time is up :(",
        color: "red"
      });
      this.incrementQuestionNumber();
      this.stopAnswerTimeout();
      this.timerCount = 10;
    },

    //...........timeouts...........//
    setAnswerTimeout() {
      this.timeout = setTimeout(() => {
        this.timerCount--;
      }, 1000);
    },
    stopAnswerTimeout() {
      clearInterval(this.timeout);
      this.timerRed = false;
    }
  }
};
</script>

<style scoped>
.question-container {
  display: grid;
  grid-template-rows: 40% 30%;
}

.question-name {
  width: 40%;
  border-radius: 5px;
}

.user-name {
  border-radius: 5px;
  background-color: #4b35a4;
  color: white;
}

.answer-item {
  width: 40%;
  border-radius: 5px;
  background-color: #15054f;
  color: white;
  cursor: pointer;
}

.timer {
  text-align: center;
  width: 75px;
  height: 75px;
  border-radius: 50%;
  shape-outside: circle(50%);
  background-color: #4b35a4;
  color: white;
}

.timeRedClass {
  background-color: darkred;
}
</style>
