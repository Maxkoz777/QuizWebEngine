<template>
  <div class="q-pr-sm row items-center">
    <div class="username-text ">
      {{ username }}
    </div>
    <div class="q-ml-md">
        <q-icon name ="account_circle" class="text-white" style="font-size: 2.5em;"/>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: "UserAppBarInfo",
  computed: {
    ...mapGetters({
      username: 'homeModule/username'
    })
  }
}
</script>

<style scoped>
.username-text{
  color:#4B35A4
}
</style>
