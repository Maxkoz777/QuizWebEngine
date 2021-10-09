<template>
  <q-btn round
         icon="add"
         class="createQuestionButton q-mb-sm q-ml-md"
         size="lg"
         @click="createQuestion"
  />
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: "CreateNewQuestion",
  methods: {
    ...mapActions({
      createQuestion: 'quizEditorModule/createQuestion'
    })
  }
}
</script>

<style scoped>
.createQuestionButton {
  background-color: #EDEDFF;
  color: #9687D0;
}
</style>
