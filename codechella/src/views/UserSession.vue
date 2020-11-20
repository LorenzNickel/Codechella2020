<template>
  <div>
    <v-card
        class="mx-auto px-5 mt-8"
        color="#8db9ca"
        dark
        max-width="400"
        tile

    >
    <v-card-title>
        <v-icon
            large
            left
        >
        mdi-amazon
        </v-icon>
        <span class="title font-weight-light">Amazon</span>
    </v-card-title>
    <h2>{{ $auth.user.name }}</h2>
    <p>{{ $auth.user.email }}</p>
    <p>{{ $auth.user.nickname }}</p>
    <p>{{ $auth.user.sub }}</p>
    
    
    <v-btn
      class="ma-2"
      v-if="$auth.isAuthenticated"
      @click="logout"
      color="button"
    >
      Log out
    </v-btn>
    </v-card>
    <!-- <div>
      <img :src="$auth.user.picture">
    </div> -->
  </div>
</template>

<script>
export default {
    name: 'session',
    methods:{
      // Log the user out
      logout() {
        this.$auth.logout({
          returnTo: window.location.origin
        });
      }
    }
}
</script>