<template>
  <div class="q-pr-sm row items-center">
    <div class="username-text text-weight-medium">
      {{ username }}
    </div>
    <q-btn round flat icon="account_circle" class="icon-color q-ml-xs" size="lg">
      <q-popup-proxy>
          <logout-button/>
      </q-popup-proxy>
    </q-btn>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import LogoutButton from "components/Logout/LogoutButton";

export default {
  name: "UserAppBarInfo",
  components: {LogoutButton},
  computed: {
    ...mapGetters({
      username: 'homeModule/username'
    })
  }
}
</script>

<style scoped>
.username-text {
  color: #4B35A4;
  font-size: 1.2rem
}

.icon-color {
  color: #4B35A4;
}
</style>
