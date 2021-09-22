<template>
  <q-btn class="accept-button" @click="openSignUpDialog" unelevated rounded>
    Sign up
  </q-btn>
</template>

<script>
export default {
  name: "SignUpButton",
  methods: {
    openSignUpDialog() {
      this.$router.push('main/register')
    }
  }
}
</script>

<style scoped>
</style>
