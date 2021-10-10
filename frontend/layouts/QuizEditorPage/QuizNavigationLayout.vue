<template>
  <div class="shadow-2 nav-menu-container" bordered style="overflow: hidden">
    <div class="q-pt-md">
      <q-tabs
        vertical
        v-model="currentQuestion_"
        indicator-color="purple"
      >
        <q-tab v-for="questionTab in questions"
               :key="questionTab.questionId"
               :name="questionTab.questionId"
               class="q-mt-md"
               no-caps
        >
          <question-tab-frame :questionData="questionTab"/>
        </q-tab>
      </q-tabs>
    </div>
    <div class="row items-center">
      <create-new-question/>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import CreateNewQuestion from "components/Quiz/Questions/CreateNewQuestion";
import QuestionTabFrame from "app/layouts/QuizEditorPage/QuestionTabFrame";

export default {
  name: "QuizNavigationLayout",
  components: {QuestionTabFrame, CreateNewQuestion},
  data() {
    return {
      chosenQuestion: null,
    }
  },
  computed: {
    ...mapGetters({
      questions: 'quizEditorModule/questions',
      currentQuestion: 'quizEditorModule/currentQuestion',
    }),
    currentQuestion_: {
      get: function () {
        return this.currentQuestion ? this.currentQuestion.questionId : ''
      },
      set: function (val) {
       this.chooseQuestion(val)
      }
    }
  },
  methods: {
    ...mapActions({
      chooseQuestion: 'quizEditorModule/chooseQuestion'
    })
  }
}
</script>

<style scoped>
.nav-menu-container {
  display: grid;
  grid-template-rows:90% 10%;
}
</style>
