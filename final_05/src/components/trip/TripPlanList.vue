<script setup>
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import VPlanCard from "@/components/common/VPlanCard.vue";
import { listPlan } from "@/api/map";

const planList = ref([]);
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const userId = userInfo.value.userId;
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
  <div class="mx-auto my-20">
    <h2 class="text-center font-bold text-2xl mb-10">여행 계획 목록</h2>
    <div
      class="w-12/12 mx-auto rounded-2xl bg-white p-5 bg-opacity-40 backdrop-filter backdrop-blur-lg">
      <div
        class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 text-center px-2 mx-auto">
        <VPlanCard
          v-for="plan in planList"
          :key="plan.planId"
          :plan="plan"
          class="flex-none shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
