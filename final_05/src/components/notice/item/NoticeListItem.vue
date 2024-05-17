<script setup>
defineProps({ article: Object });
</script>

<template>
  <tr class="border-b border-blue-gray-200">
    <td class="py-3 px-4">{{ article.articleNo }}</td>
    <td class="py-3 px-4">
      <router-link
        :to="{ name: 'notice-view', params: { articleno: article.articleNo } }"
        class="article-title link-dark">
        {{ article.subject }}
      </router-link>
    </td>
    <td class="py-3 px-4">{{ article.userId }}</td>
    <td class="py-3 px-4">{{ article.hit }}</td>
    <td class="py-3 px-4">
      {{ article.registerTime }}
    </td>
  </tr>
</template>

<style scoped></style>
