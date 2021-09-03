<template>
  <div class="full-height row items-center justify-around q-pb-lg">
        <quiz-entrance/>
        <img alt="pict" src="../../src/assets/index_pict.png" class="main-img"/>
  </div>
</template>

<script>
import QuizEntrance from "components/QuizEntrance/QuizEntrance";
export default {
  name: "IndexBodyLayout",
  components: {QuizEntrance}
}
</script>

<style scoped>
.main-img {
  max-width: 40vw;
  height: auto;
}
</style>
