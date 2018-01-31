<template>
  <v-container fluid grid-list-xs style="max-width: 1200px;">
    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 xl3 v-for="shop in preferredShops" :key="shop.id">
        <shop :shop="shop" :parent="name"></shop>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Shop from './Shop.vue'
export default {
  components: {Shop},
  data: () => ({
    name: 'preferred-shops'
  }),
  methods: {
    remove (shop) {
      this.$store.dispatch('remove', shop)
    }
  },
  computed: {
    preferredShops () {
      return this.$store.getters.preferredShops
    }
  }
}
</script>
