<script setup>
import { ref, onMounted } from "vue";
import { listSido, listTrip } from "@/api/map";

import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

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

onMounted(() => {
  getSidoList();
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
      console.log(data);

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
</script>

<template>
  <div class="fluid-container text-center mt-3 mx-20">
    <div class="flex items-center justify-center mb-10">
      <div class="mx-auto">
        <form class="flex">
          <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
          <VSelect :selectOption="typeList" @onKeySelect="onChangeType" />
          <div class="ml-5">
            <input
              type="text"
              class="h-9 min-w-[10rem] rounded-lg border-emerald-500 indent-4 text-emerald-900 shadow-lg focus:outline-none focus:ring focus:ring-emerald-500"
              v-model="param.keyword"
              placeholder="검색어..." />
            <button
              class="ml-2 h-9 min-w-[5rem] rounded-lg border text-emerald-50 shadow-lg bg-emerald-500 hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring focus:ring-emerald-500"
              type="button"
              @click="getTripList">
              검색
            </button>
          </div>
        </form>
      </div>
    </div>
    <VKakaoMap :stations="tripList" :selectStation="selectStation" />

    <!-- component -->
    <div class="bg-white my-5">
      <div
        class="overflow-x-auto border-x border-t"
        style="width: 100%; height: 500px; overflow: auto">
        <table class="table-auto w-full">
          <thead class="border-b">
            <tr class="bg-emerald-500 text-white">
              <th class="text-center p-4 font-medium">관광지명</th>
              <th class="text-center p-4 font-medium">대표이미지</th>
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
</template>

<style scoped></style>
