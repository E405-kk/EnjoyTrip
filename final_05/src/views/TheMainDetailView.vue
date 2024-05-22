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
    <div class="fluid-container text-center mb-10 mx-auto w-10/12">
      <router-link
        class="ml-4 rounded-xl bg-emerald-500 p-3 text-base mb-3 font-medium text-white transition duration-200 hover:bg-emerald-400 active:bg-emerald-600"
        :to="{ name: 'main' }"
        >목록으로 이동</router-link
      >
    </div>
  </router-view>
</template>

<style scoped></style>
