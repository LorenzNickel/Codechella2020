import Profile from "../views/Profile.vue";
import Home from "../views/Home.vue";

const router = new VueRouter({
  mode: 'history',
  routes: [
  
    {
      path: "/",
      name: "home",
      component: Home
    }
    {
      path: "/profile",
      name: "profile",
      component: Profile
    }
  ]
});

export default router;