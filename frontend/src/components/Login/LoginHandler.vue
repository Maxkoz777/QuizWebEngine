<template>
  <div>
    <login-button/>
    <login-dialog/>
  </div>
</template>

<script>
import LoginButton from "components/Login/LoginButton";
import LoginDialog from "components/Login/LoginDialog";

export default {
  name: "LoginHandler",
  components: {LoginDialog, LoginButton}
}
</script>

<style scoped>

</style>
