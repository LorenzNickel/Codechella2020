<template>
  <div>
    <v-card
        class="mx-auto  mt-15"
        color="#f5f5f5"
        max-width="800"
        tile

    >
    <v-img
        class="mt-1"
        src="../../public/codechella.jpg"
        height="350px"
    ></v-img>

    <v-card-text class="title font-weight-light">
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor 
        in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
        sunt in culpa qui officia deserunt mollit anim id est laborum."
    </v-card-text>
    <v-divider></v-divider>
    <v-bottom-navigation
        color="primary"
        dark
        horizontal
    >
        <v-btn>
            <span>LorenzNickel</span>
            <v-icon>mdi-github</v-icon>
        </v-btn>
        <v-btn>
            <span>mughees-asif</span>
            <v-icon>mdi-github</v-icon>
        </v-btn>
        <v-btn>
            <span>Nearby</span>
            <v-icon>mdi-github</v-icon>
        </v-btn>
        <v-btn>
            <span>andreabecerrab</span>
            <v-icon>mdi-github</v-icon>
        </v-btn>
    </v-bottom-navigation>
    

    </v-card>

    </div>
</template>

<script>
export default {
    name: 'intro',
    methods:{
    }
}
</script>

<style>

  .v-application .primary--text{
    background-color: #4298b5 !important;
  }
</style>