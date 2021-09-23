<template>
  <div class="fullscreen" v-if="userData">
    <home-app-bar-layout/>
    <router-view/>
  </div>
  <div v-else>
    <loading-frame/>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import HomeAppBarLayout from "app/layouts/UserPage/HomeAppBarLayout";
import LoadingFrame from "components/Loading/LoadingFrameFullSize";

export default {
  name: "HomePage",
  components: {LoadingFrame, HomeAppBarLayout},
  async created() {
    console.log('created')
    this.user = await this.fetchUserData()
    console.log(this.user)
    !this.user ? this.$router.push('/main') : ''
  },
  methods: {
    ...mapActions({
      fetchUserData: 'homeModule/fetchUserData'
    })
  },
  computed: {
    ...mapGetters({
      userData:'homeModule/userData'
    })
  }
}
</script>

<style scoped>

</style>
