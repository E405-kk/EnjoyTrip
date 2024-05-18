<script setup>
defineProps({ comment: Object });
</script>

<template>
  <div class="flex items-center mb-2">
    <img
      class="w-10 h-10 rounded-full mr-3"
      src="@/assets/plane.png"
      alt="User Avatar" />
    <div>
      <p class="font-semibold text-gray-700">{{ comment.userId }}</p>
      <p class="text-sm text-gray-500">{{ comment.registerTime }}</p>
    </div>
  </div>
  <p class="text-gray-700">{{ comment.content }}</p>
</template>

<style scoped></style>
