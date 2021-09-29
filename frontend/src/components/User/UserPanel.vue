<template>
  <div class="bg-pin column items-end q-gutter-sm q-pa-lg">
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
      <logout-button-main/>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import LogoutButtonMain from "components/Logout/LogoutButtonMain";

export default {
  name: "UserPanel",
  components: {LogoutButtonMain},
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
