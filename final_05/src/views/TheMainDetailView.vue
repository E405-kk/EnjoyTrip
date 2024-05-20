<script setup>
import VRecommend from "@/components/common/VRecommend.vue";
const props = defineProps({
  idx: Number,
  type: String,
});
const numericIdx = Number(props.idx);
const StringType = String(props.type);
</script>

<template>
  <router-view>
    <VRecommend :idx="numericIdx" :type="StringType"></VRecommend>
  </router-view>
</template>

<style scoped></style>
