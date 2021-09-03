<template>
  <q-dialog v-model="isOpen">
    <q-card>
      login form
    </q-card>
  </q-dialog>
</template>

<script>
export default {
  name: "LoginDialog",
  data() {
    return {
      isOpen: false
    }
  }
}
</script>

<style scoped>

</style>
