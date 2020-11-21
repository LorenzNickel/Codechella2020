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
        v-if="!$auth.isAuthenticated" 
        @click="login"
        color="button"
      >
      Log in
      </v-btn>
    </div>
    </v-app-bar>

    <!-- homepage -->
    <intro v-if="!$auth.isAuthenticated"></intro>
    <!-- amazon auth -->
  <user-session  v-if="$auth.isAuthenticated"></user-session>
  <!-- twtter auth -->
  <twitter-session v-if="$auth.isAuthenticated"></twitter-session>


  </div>

</template>

<script>
import UserSession from './UserSession.vue';
import TwitterSession from './TwitterSession.vue';
import Intro from './Intro.vue';


export default {
  name: "home",
  components: {
    UserSession,
    TwitterSession,
    Intro
    
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
