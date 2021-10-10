<template>
  <div class="editor-background">
    <div v-if="!currentQuestion">
      <div class="text-h6 q-pa-md no-questions-title">
        Please, choose the question
      </div>
    </div>
    <div class="row q-pa-md q-gutter-md reverse" v-if="currentQuestion">
      <q-btn flat class="bordered-btn" @click="visitUserPage" no-caps>
        Exit
      </q-btn>
      <q-btn class="accept-button" flat no-caps @click="setEditMode(true)" v-if="!editMode">
        Edit
      </q-btn>
      <q-btn class="delete-button" flat no-caps @click="deleteAndClose" v-if="!editMode">
        Delete
      </q-btn>
      <q-btn class="accept-button" flat no-caps @click="closeEditModeAndUpdate()" v-if="editMode">Save changes</q-btn>
      <q-btn class="bordered-button" flat no-caps @click="discardChanges()" v-if="editMode">Discard changes</q-btn>
    </div>
    <div class="question-container" v-if="currentQuestion">
      <div>
        <div class="row justify-center">
          <div class="bg-white question-title">
            <div class="text-h5 text-center shadow-2 q-pa-md">
              <div v-if="!editMode">
                {{ currentQuestion.question }}
              </div>
              <div v-else>
                <q-input
                  dense
                  v-model="questionDataForChange.question"
                />
              </div>
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
            <div v-for="(answer,idx) in currentQuestion.answer"
                 :key="answer.answerText"
                 class="q-pa-lg shadow-1 text-center wrong-answer"
                 :class="{'right-answer':answer.answerId === currentQuestion.rightAnswerId}"
                 style="width:40%; border-radius: 5px"
            >
              <div v-if="!editMode">
                {{ answer.answerText }}
              </div>
              <div v-else>
                <q-input
                  v-model="questionDataForChange.answer[idx].answerText"
                  dense
                />
                <q-checkbox left-label v-model="questionDataForChange.answer[idx].isRight" label="Is correct"/>
              </div>
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
  data() {
    return {
      questionDataForChange: {
        question: null,
        answer: [
          {
            "answerText": "",
            "isRight": false
          },
          {
            "answerText": "",
            "isRight": false
          },
          {
            "answerText": "",
            "isRight": false
          },
          {
            "answerText": "",
            "isRight": false
          }
        ]
      },
    }
  },
  computed: {
    ...mapGetters({
      questions: 'quizEditorModule/questions',
      currentQuestion: 'quizEditorModule/currentQuestion',
      editMode: 'quizEditorModule/editMode'
    }),
  },
  methods: {
    ...mapActions({
      setEditMode: 'quizEditorModule/setEditMode',
      updateQuestions: 'quizEditorModule/updateQuestions',
      deleteQuestion: 'quizEditorModule/deleteQuestion'
    }),
    visitUserPage() {
      this.$router.push('/')
    },
    setNewQuestionName(value, idx) {
      console.log(value, idx)
      this.questionDataForChange.answer[idx].answerText = value
    },
    closeEditModeAndUpdate() {
      this.setEditMode(false)
      this.updateQuestions(this.questionDataForChange)
      this.clearQuestionDataForChange()
    },
    discardChanges() {
      this.setEditMode(false)
      this.clearQuestionDataForChange()
    },
    clearQuestionDataForChange() {
      this.questionDataForChange.question = null
      for (const answer of this.questionDataForChange.answer) {
        answer.answerText = ""
        answer.isRight = false
      }
    },
    deleteAndClose() {
      this.deleteQuestion()
      this.discardChanges()
    }
  },
  watch: {
    currentQuestion() {
      this.discardChanges()
    }
  }
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

.no-questions-title {
  color: #4B35A4;
}

.delete-button {
  background-color: #840000;
  color: white
}
</style>
