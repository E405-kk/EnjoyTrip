<script setup>
import { ref, onMounted } from "vue";
import { listSido, listTrip } from "@/api/map";
import { addPlanList } from "@/api/map";
import { httpStatusCode } from "@/util/http-status";
import VPlanMap from "@/components/common/VPlanMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
const router = useRouter();

const sidoList = ref([]);
const tripList = ref([]);
const selectStation = ref({});
const typeList = ref([
  { text: "관광지 유형", value: 0 },
  { text: "관광지", value: 12 },
  { text: "문화시설", value: 14 },
  { text: "축제공연행사", value: 15 },
  { text: "여행코스", value: 25 },
  { text: "레포츠", value: 28 },
  { text: "숙박", value: 32 },
  { text: "쇼핑", value: 38 },
  { text: "음식점", value: 39 },
]);

const param = ref({
  sidoCode: 0,
  contentTypeId: 0,
  keyword: "",
});
const startDate = ref(null);
const endDate = ref(null);

onMounted(() => {
  if (sessionStorage.getItem("userId") == null) {
    Swal.fire("로그인이 필요한 페이지입니다!");
    router.push({ name: "user-login" });
  }
  getSidoList();
  let tripPlanButton = document.getElementById("tripPlanSaveBtn");
  tripPlanButton.addEventListener("click", function () {
    let list = document.getElementsByName("plan-list-item");
    const names = ref([]);

    for (let i = 0; i < list.length; i++) {
      let name = list[i].getAttribute("data-content-id");
      console.log(name);
      names.value.push(name);
    }

    const plan = ref({
      userId: sessionStorage.getItem("userId"),
      startDate: startDate.value,
      endDate: endDate.value,
      planList: names.value,
    });
    addPlan(plan.value);
  });
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  param.value.sidoCode = val;
};

const onChangeType = (val) => {
  param.value.contentTypeId = val;
};

const getTripList = () => {
  listTrip(
    param.value,
    ({ data }) => {
      tripList.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};

const viewStation = (trip) => {
  selectStation.value = trip;
};

const addPlan = (plan) => {
  addPlanList(
    plan,
    (response) => {
      let msg = response.data;
      if (response.status === httpStatusCode.OK) {
        msg = "계획을 저장했습니다.";
        Swal.fire({
          text: msg,
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
      console.error(error);
    }
  );
};
</script>

<template>
  <div class="my-5 text-center font-semibold text-xl">나만의 여행 계획</div>

  <div class="grid grid-cols-4 gap-4">
    <div class="col-span-3">
      <div class="fluid-container text-center mt-3 mx-auto w-11/12">
        <div class="flex items-center justify-center mb-10">
          <div class="mx-auto">
            <form class="flex">
              <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
              <VSelect :selectOption="typeList" @onKeySelect="onChangeType" />
              <div
                class="flex items-center max-w-md mx-auto bg-white rounded-lg"
                x-data="{ search: '' }">
                <div class="w-full">
                  <input
                    type="search"
                    class="w-full px-4 py-1 text-gray-800 rounded-full focus:outline-none"
                    v-model="param.keyword"
                    placeholder="search"
                    x-model="search" />
                </div>
                <div>
                  <button
                    class="flex items-center bg-emerald-500 justify-center w-12 h-12 text-white rounded-r-lg"
                    type="button"
                    @click="getTripList">
                    <svg
                      class="w-5 h-5"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                      xmlns="http://www.w3.org/2000/svg">
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                    </svg>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <VPlanMap :stations="tripList" :selectStation="selectStation" />
        <!-- component -->
        <div class="bg-white my-5">
          <div
            class="overflow-x-auto border-x border-t"
            style="width: 100%; height: 500px; overflow: auto">
            <table class="table-auto w-full">
              <thead class="border-b">
                <tr class="bg-emerald-500 text-white">
                  <th class="text-center p-4 font-medium">대표이미지</th>
                  <th class="text-center p-4 font-medium">관광지명</th>
                  <th class="text-center p-4 font-medium">주소</th>
                </tr>
              </thead>
              <tbody class="overflow-y-scroll w-full">
                <tr
                  class="text-center border-b hover:bg-gray-50"
                  v-for="trip in tripList"
                  :key="trip.contentId"
                  @click="viewStation(trip)">
                  <td class="p-2" style="width: 100px">
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
      </div>
    </div>

    <div class="col-span-1">
      <div class="mr-14">
        <button
          class="bg-white text-emerald-600 font-semibold py-2 px-4 border border-emerald-600 rounded-md shadow-md hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-opacity-50"
          id="tripPlanSaveBtn">
          저장하기
        </button>
        <div class="mt-3">
          <label for="date">
            <input type="date" id="startDate" v-model="startDate" />
            ~
            <input type="date" id="endDate" v-model="endDate" />
          </label>
        </div>
        <div id="plan-list" class="mt-3"></div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
