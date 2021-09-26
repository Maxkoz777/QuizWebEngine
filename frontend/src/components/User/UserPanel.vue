<template>
  <div class="bg-pin column items-end q-gutter-sm">
    <div>
      <q-icon name="account_circle" style="font-size: 6.5em; color: #4B35A4;"/>
    </div>
    <div class="text-h5">
      {{ firstname + '  ' + lastname }}
    </div>
    <div>
      email
    </div>
    <div>
      <logout-button/>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import LogoutButton from "components/Logout/LogoutButton";

export default {
  name: "UserPanel",
  components: {LogoutButton},
  computed: {
    ...mapGetters({
      firstname: 'homeModule/firstname',
      lastname: 'homeModule/lastname'
    })
  }
}
</script>

<style scoped>

</style>
