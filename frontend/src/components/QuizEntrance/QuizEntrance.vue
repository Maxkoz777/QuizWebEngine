<template>
  <div class="bg-white q-pa-lg quiz-entrance-container">
    <q-input color="purple" v-model="gamePin" label-color="dark-purple" class="quiz-input" filled label="Enter PIN"/>
    <quiz-enter-button @clicked="submitPin"/>

  </div>
</template>

<script>
import QuizEnterButton from "components/QuizEntrance/QuizEnterButton";

export default {
  name: "QuizEntrance",
  components: {QuizEnterButton},
  data() {
    return {
      gamePin: ''
    }
  },
  methods: {
    submitPin() {
      console.log(this.gamePin)
    }
  }
}
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
