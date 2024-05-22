<script setup>
import { ref, onMounted } from "vue";
import { planDetail, deletePlan } from "@/api/map";
import { useRouter } from "vue-router";
import VUserPlanMap from "@/components/common/VUserPlanMap.vue";
import Swal from "sweetalert2";
const router = useRouter();
const selectStation = ref({});
const planList = ref([]);
const props = defineProps({
  idx: Number,
});
const numericIdx = Number(props.idx);
onMounted(() => {
  if (sessionStorage.getItem("userId") == null) {
    Swal.fire("로그인이 필요한 페이지입니다!");
    router.push({ name: "user-login" });
  }
  console.log(numericIdx);
  getTripPlan();
});

const getTripPlan = () => {
  planDetail(
    numericIdx,
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
  Swal.fire({
    title: "계획을 삭제하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#848484",
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      deletePlan(
        numericIdx,
        (response) => {
          let msg = response.data;
          if (response.status == 200) {
            Swal.fire({
              title: "계획을 삭제했습니다.",
              text: "새로운 여행을 계획해보세요!",
              icon: "success",
            });

            router.push({ name: "trip-planList" });
          } else {
            Swal.fire({
              icon: "error",
              text: msg,
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  });
};
</script>

<template>
  <div class="my-5 text-center font-semibold text-xl">나의 여행 계획</div>
  <div class="fluid-container text-center mt-3 mx-auto w-10/12">
    <VUserPlanMap :stations="planList" :selectStation="selectStation" />
    <!-- component -->
    <div class="bg-white my-5">
      <div
        class="overflow-x-auto border-x border-t scrollbar"
        style="width: 100%; max-height: 500px; overflow-y: auto">
        <table class="table-auto w-full">
          <thead class="border-b">
            <tr class="bg-emerald-500 text-white">
              <th class="text-center p-4 font-medium">여행 순서</th>
              <th class="text-center p-4 font-medium">대표이미지</th>
              <th class="text-center p-4 font-medium">관광지명</th>
              <th class="text-center p-4 font-medium">주소</th>
            </tr>
          </thead>
          <tbody class="w-full">
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
      <router-link
        class="ml-4 rounded-xl bg-emerald-500 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-emerald-400 active:bg-emerald-600"
        :to="{ name: 'trip-planList' }"
        >목록으로 이동</router-link
      >
      <button
        @click="resetPlan"
        class="ml-4 rounded-xl bg-red-500 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-red-400 active:bg-red-600">
        여행 계획 삭제
      </button>
    </div>
  </div>
</template>

<style scoped></style>
