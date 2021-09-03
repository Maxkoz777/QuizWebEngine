<template>
  <div class="row q-pa-md reverse q-gutter-md">
    <sign-up-handler/>
    <login-handler/>
  </div>
</template>

<script>
import SignUpHandler from "components/SignUp/SignUpHandler";
import LoginHandler from "components/Login/LoginHandler";

export default {
  name: "AppBarLayout",
  components: {LoginHandler, SignUpHandler}
}
</script>

<style scoped>

</style>
