<template>
  <q-btn class="login-button" @click="openLoginDialog" rounded flat>
    login
  </q-btn>
</template>

<script>
export default {
  name: "LoginButton",
  methods: {
    openLoginDialog() {
      this.$router.push('main/login')
    }
  }
}
</script>

<style scoped>
.login-button {
  color: white
}
</style>
