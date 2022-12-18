<template>
  <q-form @submit="onCreate">
    <div class="quiz-form-container">
      <div class="q-pr-md">
        <q-input
          label="Quiz name"
          v-model="name"
          :rules="[val => (val && val.length > 0) || 'Please type quiz name']"
        />
      </div>
      <div class="column justify-center">
        <q-btn type="submit" class="accept-button" flat>
          Create quiz
        </q-btn>
      </div>
    </div>
  </q-form>
</template>

<script>
export default {
  name: "CreateQuizForm",
  data() {
    return {
      name: ""
    };
  },
  methods: {
    async onCreate() {
      if (await this.$emit("createQuiz", this.name)) {
        console.log("quiz created");
      } else {
        console.log("error");
      }
    }
  }
};
</script>

<style scoped>
.quiz-form-container {
  width: 100%;
  grid-template-columns: 65% 35%;
  display: grid;
}
</style>
