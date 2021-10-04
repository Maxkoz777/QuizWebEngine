<template>
  <q-card-section class="quiz-field row items-center justify-between">
    <div>
      <div class="text-h6">{{ name }}</div>
      <div class="text-subtitle3 text-grey-8">0 questions</div>
    </div>
    <div>
      <q-btn round flat icon="delete" class="icon-color q-ml-xs" size="lg" @click="onDelete"/>
    </div>
  </q-card-section>
</template>

<script>
export default {
  name: "QuizListItem",
  props: {
    name: {
      type: String,
      required: true
    },
    id: {
      type: Number,
      required: true
    }
  },
  methods: {
    onDelete() {
      this.$emit('deleteQuiz', this.id)
    }
  }
}
</script>

<style scoped>

</style>
