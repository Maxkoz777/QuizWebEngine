<template>
  <div class="q-pa-xc home-appbar row justify-between items-center">
    <div class="text-h6 text-white q-pl-md">
      Quiz Web Engine
    </div>
    <UserAppBarInfo/>
  </div>
</template>

<script>
import UserAppBarInfo from "components/User/UserAppBarInfo";

export default {
  name: "HomeAppBarLayout",
  components: {UserAppBarInfo}
}
</script>

<style scoped>
.home-appbar {
  background-color: #9687D0;
}
</style>
