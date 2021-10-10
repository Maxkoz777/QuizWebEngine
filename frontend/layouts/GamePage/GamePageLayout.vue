<template>
  <div class="fullscreen index-page-color">
    <div style="width: 100%; height: 100%;" class="row justify-center items-center" v-if="!playerName">
      <NameInput/>
    </div>
    <div style="width: 100%; height: 100%;" v-else>
      <quiz-game/>
    </div>
  </div>
</template>

<script>
import NameInput from "app/layouts/GamePage/NameInput/NameInput";
import {mapGetters} from "vuex";
import QuizGame from "components/QuizGame";

export default {
  name: "GamePageLayout",
  components: {QuizGame, NameInput},
  computed: {
    ...mapGetters({
      playerName: 'gameModule/playerName'
    })
  }
}
</script>

<style scoped>

</style>
