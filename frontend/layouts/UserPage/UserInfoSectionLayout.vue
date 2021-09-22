<template>
  <div class="full-height user-page-color row">
    <div class="half-width q-pa-lg row justify-center">
      quizes
    </div>
    <div class="half-width q-pa-lg row justify-center">
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
.half-width {
  width: 50%;
}
</style>
