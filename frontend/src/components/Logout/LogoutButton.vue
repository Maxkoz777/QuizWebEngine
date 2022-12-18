<template>
  <q-btn @click="logout">
    Signout
  </q-btn>
</template>

<script>
import { logout } from "src/mixins/logout";

export default {
  name: "LogoutButton",
  mixins: [logout]
};
</script>

<style scoped></style>
