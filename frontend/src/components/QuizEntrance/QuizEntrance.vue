<template>
  <div class="bg-white q-pa-lg quiz-entrance-container">
    <q-input color="purple" label-color="dark-purple" class="quiz-input" filled label="Enter PIN"/>
    <quiz-enter-button/>

  </div>
</template>

<script>
import QuizEnterButton from "components/QuizEntrance/QuizEnterButton";

export default {
  name: "QuizEntrance",
  components: {QuizEnterButton}
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
