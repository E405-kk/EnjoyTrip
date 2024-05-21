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
  <div
    class="relative flex flex-col shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300"
    style="width: 300px">
    <router-link
      class="hover:text-orange-600 absolute z-30 top-2 right-0 mt-2 mr-3"
      :to="{
        name: 'trip-listDetail',
        params: { idx: plan.planId },
      }"></router-link>
    <router-link
      class="z-20 absolute h-full w-full top-0 left-0"
      :to="{
        name: 'trip-listDetail',
        params: { idx: plan.planId },
      }"
      >&nbsp;</router-link
    >
    <div class="h-auto overflow-hidden">
      <div class="h-44 overflow-hidden relative">
        <img src="" alt="" />
      </div>
    </div>
    <div class="bg-white py-4 px-3">
      <h3 class="text-xl mb-2 font-medium">{{ plan.comment }}</h3>
      <div class="flex justify-between items-center">
        <p class="text-s text-gray-400">
          {{ formattedStartDate }} - {{ formattedEndDate }}
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
