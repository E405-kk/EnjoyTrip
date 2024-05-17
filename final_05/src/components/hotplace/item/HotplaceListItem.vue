<script setup>
defineProps({ article: Object });
</script>

<template>
  <router-link
    target="_self"
    :to="{ name: 'hotplace-view', params: { articleno: article.articleNo } }"
    class="absolute opacity-0 top-0 right-0 left-0 bottom-0">
  </router-link>
  <div class="relative mb-4 rounded-2xl">
    <img
      class="max-h-80 rounded-2xl w-full object-cover transition-transform duration-300 transform group-hover:scale-105"
      :src="article.img"
      alt="" />
    <div
      class="absolute bottom-3 left-3 inline-flex items-center rounded-lg bg-white p-2 shadow-md">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="currentColor"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="h-5 w-5 text-red-700">
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z" />
      </svg>
      <span class="ml-1 text-sm text-slate-400">{{ article.like }}</span>
    </div>

    <router-link
      target="_self"
      :to="{ name: 'hotplace-view', params: { articleno: article.articleNo } }"
      class="absolute opacity-0 top-0 right-0 left-0 bottom-0">
      자세히 보기
      <svg
        class="ml-2 w-6 h-6"
        fill="none"
        stroke="currentColor"
        viewBox="0 0 24 24"
        xmlns="http://www.w3.org/2000/svg">
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M13 5l7 7-7 7M5 5l7 7-7 7"></path>
      </svg>
    </router-link>
  </div>
  <div class="flex justify-between items-center w-full pb-4 mb-auto">
    <div class="flex items-center">
      <div class="pr-3">
        <img
          class="h-12 w-12 rounded-full object-cover"
          src="https://images.pexels.com/photos/163097/twitter-social-media-communication-internet-network-163097.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
          alt="" />
      </div>
      <div class="flex flex-1">
        <div class="">
          <p class="text-sm font-semibold">{{ article.userId }}</p>
        </div>
      </div>
    </div>
    <div class="flex justify-end">
      <div class="text-sm flex items-center text-gray-500">
        {{ article.registerTime }}
        <svg
          class="ml-1 w-4 h-4"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg">
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="1"
            d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path>
        </svg>
      </div>
    </div>
  </div>
  <h3 class="font-medium text-xl leading-8">
    <router-link
      target="_self"
      :to="{ name: 'hotplace-view', params: { articleno: article.articleNo } }"
      class="absolute opacity-0 top-0 right-0 left-0 bottom-0">
      {{ article.subject }}
    </router-link>
  </h3>
  <div></div>
</template>

<style scoped></style>
