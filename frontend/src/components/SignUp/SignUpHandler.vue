<template>
  <div>
    <sign-up-button @setDialogOpen="setDialogOpen"/>
    <sign-up-dialog :is-dialog-open.sync="isDialogOpen"/>
  </div>
</template>

<script>
import SignUpButton from "components/SignUp/SignUpButton";
import SignUpDialog from "components/SignUp/SignUpDialog";

export default {
  name: "SignUpHandler",
  components: {SignUpDialog, SignUpButton},
  data() {
    return {
      isDialogOpen: false
    }
  },
  methods: {
    setDialogOpen() {
      this.isDialogOpen = true
    }
  }
}
</script>

<style scoped>

</style>
