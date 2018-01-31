<template>
  <v-container  fluid grid-list-xs style="max-width: 1200px;">
    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 xl3 v-for="shop in nearbyShops" :key="shop.id">
        <shop :shop="shop" :parent="name"></shop>
      </v-flex>
    </v-layout>
    <infinite-loading @infinite="infiniteHandler" ref="infiniteLoading" >
      <span slot="no-more">
        No more shops around :(
      </span>
      <span slot="no-results">
        No results :( 
      </span>
    </infinite-loading>
  </v-container>
</template>

<script>
import Shop from './Shop.vue'
import InfiniteLoading from 'vue-infinite-loading'
export default {
  components: {InfiniteLoading, Shop},
  data: () => ({
    name: 'nearby-shops'
  }),
  methods: {
    infiniteHandler ($state) {
      this.$store.dispatch('loadNearbyShops')
      .then(data => {
        if (data.length > 0) {
          $state.loaded()
        } else {
          $state.complete()
        }
      }, error => {
        console.error(error)
      })
    }
  },
  computed: {
    nearbyShops () {
      return this.$store.getters.nearbyShops
    },
    geolocation () {
      return this.$store.getters.geolocation
    }
  }
}
</script>
