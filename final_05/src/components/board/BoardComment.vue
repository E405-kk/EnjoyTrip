<script setup>
defineProps({ articleno: Number });
</script>

<template>
  <div class="bg-white shadow-md rounded-lg p-5 mt-5">
    <h3 class="text-xl font-semibold text-gray-800 mb-4">댓글</h3>
    <div
      v-for="comment in comments"
      :key="comment.id"
      class="border-b border-gray-200 pb-4 mb-4">
      <div class="flex items-center mb-2">
        <img
          class="w-10 h-10 rounded-full mr-3"
          :src="comment.userAvatar"
          alt="User Avatar" />
        <div>
          <p class="font-semibold text-gray-700">{{ comment.userName }}</p>
          <p class="text-sm text-gray-500">{{ comment.time }}</p>
        </div>
      </div>
      <p class="text-gray-700">{{ comment.content }}</p>
    </div>
    <div v-if="comments.length === 0" class="text-gray-500">
      댓글이 없습니다.
    </div>
    <div class="mt-6">
      <textarea
        v-model="newComment"
        rows="3"
        class="w-full border border-gray-300 rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
        placeholder="댓글을 입력하세요"></textarea>
      <button
        @click="addComment"
        class="mt-3 bg-emerald-100 text-emerald-700 font-semibold py-2 px-4 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50">
        댓글 작성
      </button>
    </div>
  </div>
</template>

<style scoped></style>
