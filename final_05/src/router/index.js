import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "@/views/TheMainView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/detail/:idx/:type",
      name: "main-detail",
      props: true,
      component: () => import("@/views/TheMainDetailView.vue"),
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
        {
          path: "userPlan",
          name: "trip-userPlan",
          component: () => import("@/components/trip/TripUserPlan.vue"),
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
    {
      path: "/notice",
      name: "notice",
      component: () => import("@/views/TheNoticeView.vue"),
      redirect: { name: "notice-list" },
      children: [
        {
          path: "list",
          name: "notice-list",
          component: () => import("@/components/notice/NoticeList.vue"),
        },
        {
          path: "view/:articleno",
          name: "notice-view",
          component: () => import("@/components/notice/NoticeDetail.vue"),
        },
        {
          path: "write",
          name: "notice-write",
          component: () => import("@/components/notice/NoticeWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "notice-modify",
          component: () => import("@/components/notice/NoticeModify.vue"),
        },
      ],
    },
    {
      path: "/hotplace",
      name: "hotplace",
      component: () => import("@/views/TheHotplaceView.vue"),
      redirect: { name: "hotplace-list" },
      children: [
        {
          path: "list",
          name: "hotplace-list",
          component: () => import("@/components/hotplace/HotplaceList.vue"),
        },
        {
          path: "view/:articleno",
          name: "hotplace-view",
          component: () => import("@/components/hotplace/HotplaceDetail.vue"),
        },
        {
          path: "write",
          name: "hotplace-write",
          component: () => import("@/components/hotplace/HotplaceWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "hotplace-modify",
          component: () => import("@/components/hotplace/HotplaceModify.vue"),
        },
      ],
    },
  ],
});

export default router;
