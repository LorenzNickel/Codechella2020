<template>
  <div>
    <v-app-bar
      color="primary"
      dense
      dark
    >
      <v-toolbar-title>Codechella</v-toolbar-title>
      <v-spacer></v-spacer>
      <!--LOGIN BTN-->
      <div v-if="!$auth.loading">
      <!-- show login when not authenticated -->
      <v-btn
        class="ma-2"
        :loading="loading"
        :disabled="loading"
        v-if="!$auth.isAuthenticated" 
        @click="login"
        color="button"
      >
      Log in
      </v-btn>
      <v-btn
        class="ma-2"
        :loading="loading"
        :disabled="loading"
        v-if="$auth.isAuthenticated"
        @click="logout"
        color="button"
      >
        Log out
      </v-btn>
    </div>


    </v-app-bar>
      <div class="home">

  </div>
  </div>

</template>

<script>
// .. imports removed for brevity

export default {
  name: "home",
  components: {
    
  },
  methods: {
    // Log the user in
    login() {
      this.$auth.loginWithRedirect();
    },
    // Log the user out
    logout() {
      this.$auth.logout({
        returnTo: window.location.origin
      });
    }
  }
};
</script>