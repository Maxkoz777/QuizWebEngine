<template>
  <div class="editor-background">
    <div class="row q-pa-md q-gutter-md reverse">
      <q-btn flat class="bordered-btn" @click="visitUserPage" no-caps>
        Exit
      </q-btn>
      <q-btn class="accept-button" flat no-caps>
        Edit
      </q-btn>
    </div>
    <div class="question-container" v-if="currentQuestion">
      <div>
        <div class="row justify-center">
          <div class="bg-white question-title">
            <div class="text-h5 text-center shadow-2 q-pa-md">
              {{currentQuestion.question}}
            </div>
          </div>
        </div>
      </div>
      <div class="row justify-center">
        <div style="width:80%">
          <div class="text-h6 text-center" style="color: #4B35A4">
            Select correct answer:
          </div>
          <div class="row q-pt-lg q-gutter-lg justify-center">
            <div v-for="answer in currentQuestion.answer"
                 :key="answer.answerText"
                 class="q-pa-lg shadow-1 text-center wrong-answer"
                 :class="{'right-answer':answer.answerId === currentQuestion.rightAnswerId}"
                 style="width:40%; border-radius: 5px"
            >
              {{ answer.answerText }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: "QuestionEditorLayout",
  computed: {
    ...mapGetters({
      questions: 'quizEditorModule/questions',
      currentQuestion: 'quizEditorModule/currentQuestion',
    })
  },
  methods: {
    visitUserPage() {
      this.$router.push('/')
    }
  },
}
</script>

<style scoped>
.bordered-btn {
  color: #4B35A4;
  border: 1px solid #4B35A4;
}

.editor-background {
  background-color: #E8E8FF;
}

.question-container {
  height: 100%;
  display: grid;
  grid-template-rows: 25% 75%;
}

.question-title {
  width: 40vw;
  border-radius: 5px;
}

.wrong-answer {
  background-color: #EE8585;
}

.right-answer {
  background-color: #D6FFFA;
}
</style>
