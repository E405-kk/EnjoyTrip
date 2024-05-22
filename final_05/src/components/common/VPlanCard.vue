<script setup>
import { computed } from "vue";
import dayjs from "dayjs";

const props = defineProps({
  plan: Object,
});

const formattedStartDate = computed(() => {
  return dayjs(props.plan.startDate).format("YYYY-MM-DD");
});

const formattedEndDate = computed(() => {
  return dayjs(props.plan.endDate).format("YYYY-MM-DD");
});
console.log(props.plan);
</script>

<template>
  <router-link
    class="transition-all duration-1000 bg-white hover:bg-green-500 hover:shadow-xl m-2 p-4 relative z-40 group"
    :to="{
      name: 'trip-listDetail',
      params: { idx: plan.planId },
    }">
    <div
      class="absolute bg-green-500/50 top-0 left-0 w-24 h-1 z-30 transition-all duration-200 group-hover:bg-white group-hover:w-1/2"></div>
    <div class="py-2 px-9 relative">
      <h3 class="mt-8 text-lg font-semibold text-black group-hover:text-white">
        {{ plan.comment }}
      </h3>
      <p class="mt-4 text-base text-gray-600 group-hover:text-white">
        {{ formattedStartDate }} - {{ formattedEndDate }}
      </p>
    </div>
  </router-link>
</template>

<style scoped></style>
