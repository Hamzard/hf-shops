<template>
  <div>
    <v-toolbar color="white" persistent>
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">Nearby Shops</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-side-icon @click.stop="drawer = !drawer" class="hidden-md-and-up"></v-toolbar-side-icon>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn v-for="item in menuItems" :key="item.title" router :to="item.link" flat>
          <v-icon :color="item.color" left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
        <v-btn v-if="isAuthenticated" @click="onLogout" flat>
          <v-icon left dark>exit_to_app</v-icon>
          Logout
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-navigation-drawer temporary v-model="drawer" light absolute>
      <v-list class="pt-0" dense>
        <v-divider></v-divider>
        <v-list-tile v-for="item in menuItems" :key="item.title" router :to="item.link">
          <v-list-tile-action>
            <v-icon :color="item.color">{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile v-if="isAuthenticated" @click="onLogout">
          <v-list-tile-action>
            <v-icon>exit_to_app</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Logout</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  data () {
    return {
      drawer: false
    }
  },
  methods: {
    onLogout () {
      this.$store.dispatch('logout')
      this.$router.push('/')
    }
  },
  computed: {
    menuItems () {
      let list = [
        { icon: 'send', title: 'Login', link: '/login', color: 'purple' },
        { icon: 'face', title: 'Register', link: '/register', color: 'blue' }
      ]
      if (this.isAuthenticated) {
        list = [
          { icon: 'pin_drop', title: 'Nearby shops', link: '/nearby', color: 'green' },
          { icon: 'favorite', title: 'Preferred shops', link: '/preferred', color: 'red' }
        ]
      }
      return list
    },
    isAuthenticated () {
      return this.$store.getters.isAuthenticated
    }
  }
}
</script>
