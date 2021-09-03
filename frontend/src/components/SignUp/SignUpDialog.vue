<template>
  <q-dialog v-model="isOpen">
    opened
  </q-dialog>
</template>

<script>

export default {
  name: "SignUpDialog",
  props: ['isDialogOpen'],
  computed: {
    isOpen: {
      get() {
        return this.isDialogOpen
      },
      set(val) {
        this.$emit("update:isDialogOpen", val)
      }
    }
  }
}
</script>

<style scoped>

</style>
