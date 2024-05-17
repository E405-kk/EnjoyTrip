<script setup>
import { ref, onMounted } from "vue";
import { getPlanList, deletePlanList } from "@/api/map";
import { storeToRefs } from "pinia";
import { httpStatusCode } from "@/util/http-status";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";
import VUserPlanMap from "@/components/common/VUserPlanMap.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const selectStation = ref({});
const planList = ref([]);

onMounted(() => {
  getTripList();
});

const getTripList = () => {
  getPlanList(
    userInfo.value,
    ({ data }) => {
      planList.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};
const viewStation = (trip) => {
  selectStation.value = trip;
};

const resetPlan = () => {
  deletePlanList(
    userInfo.value,
    (response) => {
      if (response.status === httpStatusCode.OK) {
        console.log("계획 삭제 성공");
        getTripList();
        router.push({ name: "trip-plan" });
      }
    },
    (err) => {
      console.log(err);
    }
  );
};
</script>

<template>
  <div class="my-5 text-center font-semibold text-xl">나의 여행 계획</div>
  <div class="fluid-container text-center mt-3 mx-60">
    <VUserPlanMap :stations="planList" :selectStation="selectStation" />
    <!-- component -->
    <div class="bg-white my-5">
      <div
        class="overflow-x-auto border-x border-t"
        style="width: 100%; height: 500px; overflow: auto">
        <table class="table-auto w-full">
          <thead class="border-b">
            <tr class="bg-emerald-500 text-white">
              <th class="text-center p-4 font-medium">여행 순서</th>
              <th class="text-center p-4 font-medium">대표이미지</th>
              <th class="text-center p-4 font-medium">관광지명</th>
              <th class="text-center p-4 font-medium">주소</th>
            </tr>
          </thead>
          <tbody class="overflow-y-scroll w-full">
            <tr
              class="text-center border-b hover:bg-gray-50"
              v-for="(trip, index) in planList"
              :key="trip.contentId"
              @click="viewStation(trip)">
              <td class="p-2" style="width: 100px">
                {{ index + 1 }}
              </td>
              <td class="p-2" style="width: 70px">
                <img :src="trip.firstImage" />
              </td>
              <td class="p-3" style="width: 200px">{{ trip.title }}</td>
              <td class="p-3" style="width: 300px">
                {{ trip.addr1 }} {{ trip.addr2 }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div>
      <button
        @click="resetPlan"
        class="ml-4 rounded-xl bg-red-500 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-red-400 active:bg-red-600">
        여행 계획 삭제
      </button>
    </div>
  </div>
</template>

<style scoped></style>
