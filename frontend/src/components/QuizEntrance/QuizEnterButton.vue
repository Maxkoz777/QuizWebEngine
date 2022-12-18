<template>
  <q-btn
    class="full-width quiz-entrance-button q-mt-sm"
    unelevated
    @click="clicked"
  >
    ENTER
  </q-btn>
</template>

<script>
export default {
  name: "QuizEnterButton",
  methods: {
    clicked() {
      this.$emit("clicked");
    }
  }
};
</script>

<style scoped>
.quiz-entrance-button {
  background-color: #15054f;
  color: white;
  border-radius: 5px;
}
</style>
