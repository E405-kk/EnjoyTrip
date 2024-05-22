<script setup>
const props = defineProps({ article: Object });
function getImageUrl() {
  if (props.article.img) {
    var url = "/src/assets/users/";
    url += props.article.img;
    return url;
  } else {
    return "/src/assets/user.png";
  }
}
</script>

<template>
  <div class="my-2">
    <h2 class="text-gray-800 text-2xl font-bold px-5 mb-5 border-b pb-3">
      {{ article.articleNo }}. {{ article.subject }}
    </h2>
  </div>
  <div class="flex items-center justify-between mb-5">
    <div class="flex items-center">
      <img
        class="w-12 h-12 mr-3 rounded-full bg-gray-100"
        :src="getImageUrl()"
        alt="User Avatar" />
      <div>
        <p class="font-bold text-lg text-gray-700">
          {{ article.userId }}
        </p>
        <p class="text-sm text-gray-500">{{ article.registerTime }}</p>
      </div>
    </div>
    <div class="text-right text-gray-500">조회 : {{ article.hit }}</div>
  </div>
  <div class="border-t my-3"></div>
  <div class="text-gray-700 text-lg mx-5 min-h-96 whitespace-pre-line">
    {{ article.content }}
  </div>
</template>

<style scoped></style>
