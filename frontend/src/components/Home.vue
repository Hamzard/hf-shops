<template>
  <v-container fluid cliped>
    <v-layout row justify-center>
      <v-flex >
        <v-layout column align-center>
          <center v-if="!isAuthenticated">
            <h1>Discover interesting shops</h1>
            <h4 class="text-md-right">near you</h4>
            <v-btn color="green" dark @click="onGetStarted">Get Started</v-btn>
          </center>
          <v-container v-if="featuredShops.length > 0" grid-list-xs >
            <h2>Most liked shops</h2>
            <v-layout row wrap>
              <v-flex xs12 sm6 md4 lg3 xl3 v-for="shop in featuredShops" :key="shop.id">
                <shop :shop="shop"></shop>
              </v-flex>
            </v-layout>
          </v-container>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Shop from './shop/Shop.vue'
export default {
  components: {
    Shop
  },
  data: () => ({
  }),
  methods: {
    onGetStarted () {
      this.$router.push('/register')
    }
  },
  computed: {
    featuredShops () {
      return this.$store.getters.featuredShops
    },
    isAuthenticated () {
      return this.$store.getters.isAuthenticated
    }
  },
  beforeCreate () {
    this.$store.dispatch('loadFeaturedShops')
  }
}
</script>
