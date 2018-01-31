<template>
  <v-footer fixed>
    <span v-if="geolocation">
      Lon: {{geolocation.lon}}<br>
      Lat: {{geolocation.lat}}
    </span>
    <v-spacer></v-spacer>
    <a :href="githubRepoLink" target="_blank">View source</a>
  </v-footer>
</template>

<script>
export default {
  data () {
    return {
      githubRepoLink: 'https://github.com/Hamzard/hf-shops'
    }
  },
  computed: {
    geolocation () {
      return this.$store.getters.geolocation
    }
  }
}
</script>
