import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Courses from "./views/Courses.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/courses",
      name: "Courses",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: Courses
    }
  ]
});
