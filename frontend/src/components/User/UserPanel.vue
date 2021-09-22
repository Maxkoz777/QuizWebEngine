<template>
  <div>
    <div>
      <q-icon name="account_circle" style="font-size: 5.5em; color: #4B35A4;"/>
    </div>
    <div class="text-h6">
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
