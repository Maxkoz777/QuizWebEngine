<template>
  <div class="fullscreen index-page-color">
    <div style="width: 100%; height: 100%;" class="row justify-center items-center">
      <NameInput/>
    </div>
  </div>
</template>

<script>
import NameInput from "app/layouts/GamePage/NameInput/NameInput";

export default {
  name: "GamePageLayout",
  components: {NameInput}
}
</script>

<style scoped>

</style>
