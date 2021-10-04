<template>
  <div class="frame-container q-pa-xs">
    <div class="question-title text-grey-8">{{ questionData.question }}</div>
    <div class="row q-gutter-md q-pa-xs justify-center">
      <div v-for="answer in questionData.answer"
           :key=answer.answerText
           class="answer-indicator q-pa-xs wrong-answer"
           :class="{'right-answer':answer.isRight}"
      >
        {{answer.answerText}}
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "QuestionTabFrame",
  props: {
    questionData: {
      type: Object,
      required: true
    },
  },
  computed: {
    ...mapGetters({
      currentQuestion: 'quizEditorModule/currentQuestion',
    })
  }
}
</script>

<style scoped>
.frame-container {
  background-color: #EDEDFF;
  border-radius: 5px;
  width: 15vw;
}

.question-title {
  font-size: 0.9em;
}

.answer-indicator {
  width: 40%;
  border-radius: 5px;
  font-size: 0.8em;
  overflow:hidden;
  text-overflow:ellipsis;

}

.wrong-answer{
  background-color: #EE8585;
}
.right-answer{
  background-color: #D6FFFA;
}
</style>
