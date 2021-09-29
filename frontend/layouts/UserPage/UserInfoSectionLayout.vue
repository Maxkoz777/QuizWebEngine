<template>
  <div class="full-height user-page-color row q-pa-lg">
    <div class="quizes-layout q-pa-lg row justify-center ">
      <user-quizez-layout/>
    </div>
    <div class="user-panel-container q-pa-md row justify-end">
      <user-panel/>
    </div>
  </div>
</template>

<script>
import UserPanel from "components/User/UserPanel";
import UserQuizezLayout from "app/layouts/UserPage/UserQuizezLayout";

export default {
  name: "UserInfoSectionLayout",
  components: {UserQuizezLayout, UserPanel}
}
</script>

<style scoped>
.user-panel-container {
  width: 40%;
}

.quizes-layout {
  width: 60%
}
</style>
