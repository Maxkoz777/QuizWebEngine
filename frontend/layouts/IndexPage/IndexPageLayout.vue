<template>
  <div class="fullscreen index-page-color">
    <!-- Sign In/ Sign Up routing   -->
    <router-view/>
    <app-bar-layout/>
    <index-body-layout/>
  </div>
</template>

<script>


import AppBarLayout from "app/layouts/IndexPage/AppBarLayout";
import IndexBodyLayout from "app/layouts/IndexPage/IndexBodyLayout";

export default {
  name: "IndexPageLayout",
  components: {IndexBodyLayout, AppBarLayout},
}
</script>

<style scoped lang="scss">

</style>
