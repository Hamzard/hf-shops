<template>
  <v-container>
    <v-layout row v-if="error">
      <v-flex xs12 sm6 offset-sm3>
        <app-alert @dismissed="onDismissed" :text="error"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-form v-model="valid" @submit.prevent="onRegister">
          <v-card>
            <v-card-title light><h1>Register</h1></v-card-title>
            <v-card-text>
              <v-layout row>
                <v-flex xs12>
                  <v-text-field
                    name="email"
                    label="Email"
                    placeholder="name@email.com"
                    id="email"
                    v-model="email"
                    type="email"
                    clearable
                    :rules="emailRules"
                    required>
                  </v-text-field>
                </v-flex>
              </v-layout>
              <v-layout row>
                <v-flex xs12>
                  <v-text-field
                    name="password"
                    label="Password"
                    id="password"
                    v-model="password"
                    type="password"
                    :rules="passwordRules"
                    required>
                  </v-text-field>
                </v-flex>
              </v-layout>
              <v-layout row>
                <v-flex xs12>
                  <v-text-field
                    name="confirmPassword"
                    label="Confirm password"
                    id="confirmPassword"
                    v-model="confirmPassword"
                    type="password"
                    :rules="[comparePasswords]"
                    required
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn type="submit" :disabled="!validateForm">Register</v-btn>
            </v-card-actions>
          </v-card>
        </v-form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data () {
    return {
      email: '',
      password: '',
      confirmPassword: '',
      valid: false,
      passwordRules: [
        (v) => !!v || 'Password is required',
        (v) => v.length >= 6 || 'Password must be atleast 6 characters'
      ],
      emailRules: [
        (v) => !!v || 'E-mail is required',
        (v) => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ]
    }
  },
  methods: {
    onDismissed () {
      this.$store.dispatch('clearError')
    },
    onRegister () {
      this.$store.dispatch('register', { email: this.email, password: this.password })
      .then(data => {
        this.$store.dispatch('login', { email: this.email, password: this.password })
      }, error => {
        console.error(error)
      })
    }
  },
  computed: {
    comparePasswords () {
      return this.password !== this.confirmPassword ? 'Passwords do not match' : true
    },
    isAuthenticated () {
      return this.$store.getters.isAuthenticated
    },
    error () {
      return this.$store.getters.error
    },
    validateForm () {
      return this.valid && (this.password === this.confirmPassword)
    }
  },
  watch: {
    isAuthenticated (isAuthenticated) {
      if (isAuthenticated) {
        this.$router.push('/nearby')
        this.$store.dispatch('loadPreferredShops')
      }
    }
  }
}
</script>
