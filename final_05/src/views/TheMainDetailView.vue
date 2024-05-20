<script setup>
import VRecommend from "@/components/common/VRecommend.vue";
import { useRoute } from "vue-router";
const route = useRoute();
const { idx } = route.params;
console.log(idx);
</script>

<template>
  <router-view>
    <VRecommend :idx="idx"></VRecommend>
  </router-view>
</template>

<style scoped></style>
