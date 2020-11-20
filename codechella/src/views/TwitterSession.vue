<template>
    <div>
        <v-card
        class="mx-auto mt-8"
        color="#26c6da"
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

    <!--LOGIN BTN-->
      <div >
      <!-- show login when not authenticated -->
      <v-btn
        class="ma-2"

        @click="login"
        color="button"
      >
      Log in
      </v-btn>
      <v-btn
        class="ma-2"
    
        @click="logout"
        color="button"
      >
        Log out
      </v-btn>
    </div>
    <v-card-actions>
      <v-list-item class="grow">
        <v-list-item-avatar color="grey darken-3">
          <v-img
            class="elevation-6"
            alt=""
            src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          ></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>Evan You</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
        </v-card-actions>
    </v-card>
  </div>

</template>

<script>


export default {
  name: "twitter",
  components: {
    
  },
  methods: {
    
  }
};
</script>