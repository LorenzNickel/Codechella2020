<template>
    <div>
      <v-card
        class="mx-auto mt-8 px-5"
        color="#74d2e7"
        dark
        max-width="400"
    >
      <v-card-title>
          <v-icon
              large
              left
          >
          mdi-twitter
          </v-icon>
          <span class="title font-weight-light">Twitter</span>
      </v-card-title>
      <h2 v-if="this.logged">{{ this.user.name }}</h2>
      <p  v-if="this.logged">{{ this.user.email }}</p>


    <!--LOGIN BTN-->
      <div >
      <!-- show login when not authenticated -->
      <v-btn
        class="ma-2"
        color="button"
        @click.prevent="connect"
        v-if="!logged"
      >
      Log in
      </v-btn>
      <v-btn
        class="ma-2"
        color="button"
        v-if="this.logged"
        @click="logout"
      >
        Log out
      </v-btn>
    </div>
      </v-card>
      <v-card
        v-if="this.logged"
        class="mx-auto mt-8 px-5"
        color="primary"
        dark
        max-width="400"
        >
        <v-card-title>
          <v-icon
            large
            left
          >
            mdi-transit-connection-horizontal
        </v-icon>
      <span class="title font-weight-light">Connections</span>
      </v-card-title>
      <v-row class="px-5">
        <v-icon small color="#89ba16" v-if="this.status"> mdi-check-bold</v-icon>
        <p class="title font-weight-light">Amazon</p>
      </v-row>
      <v-row class="px-5">
        <v-icon small color="#89ba16" v-if="this.status">mdi-check-bold</v-icon>
        <p class="title font-weight-light">Twitter</p>
      </v-row>

      <v-btn
          class="ma-2"
          v-if="this.logged && !this.status"
          @click="save"
          color="button"
      >
        Save both
      </v-btn>
    </v-card>
  </div>

</template>

<script>
import Pizzly from '../../node_modules/pizzly-js'
import auth from '../configuration/user'
import Credentials from '../services/Credentials'


export default {
  name: "twitter",
  data: function () {
    return {
      user: null,
      pizzly: '',
      logged : false,
      status: false
    }
  },

  components: {
    
  },
  mounted: function() {
  // Here we initialize Pizzly.
  this.pizzly = new Pizzly({
    host: "codechella2020.herokuapp.com/",
  });
},
  methods: {
    connect: async function() {
      //set conf session
      this.user = auth

      const myAPI = this.pizzly.integration('twitter')

        await myAPI.connect()
        .then((auth) => {
          this.user = auth
          console.log('Successfully logged in!')
        }).catch(this.connectError);
        this.logged = true
    },
    connectSuccess: function(data) {
      // On success, we update the user object
      this.user = data.authId;
      console.log('Successfully logged in!')
    },
    connectError: function (err) {
      console.log(err)
    },
    logout: function(){
      this.logged = false
    },
    save: async function(){
      const cred= {
          amzn_user_id: this.$auth.user.sub.substring(7, this.$auth.user.sub.length),
          amzn_name: this.$auth.user.name,
          amzn_email: this.$auth.user.email,
          twitter_accessToken: this.user.accessToken,
          twitter_secretToken: this.user.tokenSecret
      }
      let status = await Credentials.saveCredentials(cred)
      console.log(status)
      if(status === 200){
        this.status = true
      }
    }
    
  }
};
</script>
<style>
  p {
    margin: 0 !important;
  }

</style>