<template>
  <div class="quiz-list-container q-pa-lg">
    <q-card class="my-card">
      <q-card-section class="quizzes-card-background">
        <div class="text-h6">My Quizzes:</div>
      </q-card-section>

      <q-separator/>
      <div class="q-pt-md">
        <div class="q-pr-md q-pl-md q-gutter-md" v-if="quizList.length!==0">
          <quiz-list-item v-for="item in quizList" :name="item.name" :id="item.quizId" :key="item.quizId"
                          @deleteQuiz="deleteQuiz"/>
        </div>
        <div v-else class="q-pa-md">
          No quizzes, create one!
        </div>
      </div>
      <q-card-section>
        <create-quiz-form @createQuiz="createQuiz"/>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import QuizListItem from "components/User/QuizListItem";
import {mapActions, mapGetters} from 'vuex'
import CreateQuizForm from "components/User/CreateQuizForm";

export default {
  name: "UserQuizzesLayout",
  components: {CreateQuizForm, QuizListItem},
  methods: {
    ...mapActions({
      deleteQuiz: 'homeModule/deleteQuiz',
      createQuiz: 'homeModule/createQuiz',
      chooseQuestion: 'quizEditorModule/chooseQuestion'
    })
  },
  computed: {
    ...mapGetters({
      quizList: 'homeModule/quizList'
    })
  },
  mounted() {
    this.chooseQuestion(null)
  }
}
</script>

<style scoped>
.quizzes-card-background {
  background-color: #9687D0;
  color: #2C0048;
}

.quiz-field {
  background-color: #FBD8B6;
  color: #000000;
  border-radius: 5px;
}

.quiz-list-container {
  min-width: 80%
}

</style>
