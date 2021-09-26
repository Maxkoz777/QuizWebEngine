<template>
  <div class="full-height user-page-color row q-pa-lg">
    <div class="quizes-layout q-pa-lg row justify-center ">
      quizes
    </div>
    <div class="user-panel-container q-pa-lg row justify-end">
      <user-panel/>
    </div>
  </div>
</template>

<script>
import UserPanel from "components/User/UserPanel";

export default {
  name: "UserInfoSectionLayout",
  components: {UserPanel}
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
