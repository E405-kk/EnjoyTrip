import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "@/views/TheMainView.vue";
import TheTripView from "@/views/TheTripView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/trip",
      name: "trip",
      component: () => import("@/views/TheTripView.vue"),
      children: [
        {
          path: "search",
          name: "trip-search",
          component: () => import("@/components/trip/TripSearch.vue"),
        },
        {
          path: "plan",
          name: "trip-plan",
          component: () => import("@/components/trip/TripPlan.vue"),
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "modify",
          name: "user-modify",
          component: () => import("@/components/users/UserModify.vue"),
        },
        {
          path: "findPwd",
          name: "user-findPwd",
          component: () => import("@/components/users/UserFindPwd.vue"),
        },
        {
          path: "changePwd",
          name: "user-changePwd",
          component: () => import("@/components/users/UserChangePwd.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("@/views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
  ],
});

export default router;
