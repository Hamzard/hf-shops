<template>
  <v-card>
    <v-card-title> <h2>{{shop.name}}</h2> </v-card-title>
    <v-card-media :src="shop.picture" height="200px"></v-card-media>
    <v-card-actions>
      <v-btn v-if="parent === 'preferred-shops'" flat color="red" @click="remove(shop)">Remove</v-btn>
      <v-btn v-if="parent === 'nearby-shops'" flat color="red" @click="dislike(shop)">Dislike</v-btn>
      <v-spacer></v-spacer>
      <v-btn v-if="parent === 'nearby-shops'" flat color="green" @click="like(shop)">Like</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'shop',
  props: ['shop', 'parent'],
  methods: {
    like (shop) {
      this.$store.dispatch('like', shop)
    },
    dislike (shop) {
      this.$store.dispatch('dislike', shop)
    },
    remove (shop) {
      this.$store.dispatch('remove', shop)
    }
  },
  computed: {}
}
</script>

<style>
.card{
  margin:10px 10px
}
.card__media{
  margin:0 10px 0 10px
}
</style>
