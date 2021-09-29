<template>
  <div class="quiz-list-container q-pa-lg">
    <q-card class="my-card">
      <q-card-section class="quizzes-card-background">
        <div class="text-h6">My Quizzes:</div>
      </q-card-section>

      <q-separator/>
      <div class="q-pt-md q-gutter-md">
        <div class="q-pr-md q-pl-md">
          <q-card-section class="quiz-field row items-center justify-between">
            <div>
              <div class="text-h6">Quiz1</div>
              <div class="text-subtitle3 text-grey-8">0 questions</div>
            </div>
            <div>
              <q-btn round flat icon="delete" class="icon-color q-ml-xs" size="lg"/>
            </div>
          </q-card-section>
        </div>
      </div>
      <q-card-section class="row justify-center">
        <q-btn class="accept-button q-pl-lg q-pr-lg" flat>
          Create new quiz
        </q-btn>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
export default {
  name: "UserQuizezLayout"
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
