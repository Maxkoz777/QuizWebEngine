<template>
  <q-btn @click="logout">
    Signout
  </q-btn>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: "LogoutButton",
  methods: {
    ...mapActions({
      logout: 'homeModule/logout'
    })
  }
}
</script>

<style scoped>

</style>
