<template>
  <div class="quiz-editor-container" style="height: 100%">
    <quiz-navigation-layout />
    <question-editor-layout />
  </div>
</template>

<script>
import { mapActions } from "vuex";
import QuizNavigationLayout from "app/layouts/QuizEditorPage/QuizNavigationLayout";
import QuestionEditorLayout from "app/layouts/QuizEditorPage/QuestionEditorLayout";

export default {
  name: "QuizEditorSection",
  components: { QuestionEditorLayout, QuizNavigationLayout },
  methods: {
    ...mapActions({
      fetchQuizData: "quizEditorModule/fetchQuizData"
    })
  },
  mounted() {
    this.fetchQuizData(this.$route.params.id);
  }
};
</script>

<style scoped>
.quiz-editor-container {
  display: grid;
  grid-template-columns: 20% 80%;
}
</style>
