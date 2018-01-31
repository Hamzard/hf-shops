<template>
  <v-app>
    <app-header></app-header>
    <main>
      <router-view></router-view>
    </main>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import Header from './components/navigation/Header.vue'
import Footer from './components/navigation/Footer.vue'
export default {
  components: {
    'app-header': Header,
    'app-footer': Footer
  },
  data () {
    return { }
  }
}
</script>

<style>
main{
  margin-bottom: 100px
}
</style>
