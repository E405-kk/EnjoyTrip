<script setup>
import { onMounted, ref } from "vue";
import VPlanCard from "@/components/common/VPlanCard.vue";
import { listPlan } from "@/api/map";

const planList = ref([]);
const userId = sessionStorage.getItem("userId");
onMounted(() => {
  getPlanList();
});

const getPlanList = async () => {
  await listPlan(
    userId,
    ({ data }) => {
      planList.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};
</script>

<template>
  <div id="rank" class="mx-auto my-20" style="width: 80%">
    <h2 class="text-left font-bold text-2xl ml-4">여행 계획</h2>
    <div class="py-5 flex overflow-x-auto scrollbar">
      <VPlanCard
        v-for="plan in planList"
        :key="plan.planId"
        :recommend="plan"
        class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm mr-4" />
    </div>
  </div>
</template>

<style scoped></style>
