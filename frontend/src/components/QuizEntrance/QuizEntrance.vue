<template>
  <div class="bg-white q-pa-lg quiz-entrance-container">
    <q-input
      data-test-id="enterPIN"
      color="purple"
      v-model="gamePin"
      label-color="dark-purple"
      class="quiz-input"
      filled
      label="Enter PIN"
      @keydown.enter="submitPin"
    />
    <quiz-enter-button       data-test-id="enterPINSubmit"
                             @clicked="submitPin" />
  </div>
</template>

<script>
import QuizEnterButton from "components/QuizEntrance/QuizEnterButton";
import { mapActions } from "vuex";

export default {
  name: "QuizEntrance",
  components: { QuizEnterButton },
  data() {
    return {
      gamePin: ""
    };
  },
  methods: {
    ...mapActions({
      validateQuizPin: "gameModule/validateQuizPin",
      removeQuizData: "gameModule/removeQuizData"
    }),
    async submitPin() {
      let result = await this.validateQuizPin(this.gamePin);
      if (result) {
        await this.$router.push(`/game/${this.gamePin}`);
      } else {
        this.showQuizEnterError();
      }
    },
    showQuizEnterError() {
      this.$q.notify({
        message: "There is no yet quiz with such PIN :(",
        icon: "announcement",
        color: "red"
      });
    }
  },
  mounted() {
    this.removeQuizData();
  }
};
</script>

<style scoped>
.quiz-input {
  background-color: #d8bfe7;
  border-radius: 5px;
}

.quiz-entrance-container {
  border-radius: 5px;
  width: 18vw;
}
</style>
