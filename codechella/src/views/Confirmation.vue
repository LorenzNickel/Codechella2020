<template>
    <div>
        <v-card
        class="mx-auto mt-8 px-5 py-5"
        color="#0085ad"
        dark
        max-width="400"
        >
            <v-btn
            class="mx-auto"
            depressed
            color="button3"
            >
                <v-icon largeleft>mdi-check-bold</v-icon>
            <span class="title font-weight-light">Confirm connection</span>
            </v-btn>
        </v-card>
    </div>

</template>

<script>
import Pizzly from '../../node_modules/pizzly-js'
import auth from '../configuration/user'


export default {
  name: "confirmation",
  data: function () {
    return {
      user: null,
      pizzly: '',
      logged : false
    }
  },

  components: {
    
  },
  mounted: function() {
  // Here we initialize Pizzly.
  this.pizzly = new Pizzly({
    host: "my-pizzly.herokuapp.com/",
  });
},
  methods: {
    connect: async function() {
      //set conf session
      this.user = auth

      const myAPI = this.pizzly.integration('twitter')

        myAPI.connect()
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
    }
    
  }
};
</script>