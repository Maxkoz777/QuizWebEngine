<template>
  <q-btn
    data-test-id="createQuestionSection"
    round
    icon="add"
    class="createQuestionButton q-mb-sm q-ml-md"
    size="lg"
    @click="createQuestion"
  />
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "CreateNewQuestion",
  methods: {
    ...mapActions({
      createQuestion: "quizEditorModule/createQuestion"
    })
  }
};
</script>

<style scoped>
.createQuestionButton {
  background-color: #ededff;
  color: #9687d0;
}
</style>
