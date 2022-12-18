<template>
  <q-card-section
    class="quiz-field row items-center justify-between cursor-pointer"
    @click="visitQuiz(id)"
    v-ripple
  >
    <div>
      <div class="text-h6">{{ name }}</div>
      <div class="text-subtitle3 text-grey-8">{{ quizCount }} questions</div>
    </div>
    <div>
      <q-btn
        round
        flat
        icon="delete"
        class="icon-color q-ml-xs"
        size="lg"
        @click.stop="onDelete"
      />
    </div>
  </q-card-section>
</template>

<script>
import { mapActions } from "vuex";

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
    },
    quizCount: {
      type: Number,
      required: true
    }
  },
  methods: {
    ...mapActions({
      visitQuiz: "homeModule/visitQuiz"
    }),
    onDelete() {
      this.$emit("deleteQuiz", this.id);
    }
  }
};
</script>

<style scoped></style>
