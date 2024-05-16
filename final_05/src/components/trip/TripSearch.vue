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
  <div class="container text-center mt-3">
    <div class="row mb-2">
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
      </div>
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="typeList" @onKeySelect="onChangeType" />
      </div>
      <div class="col d-flex flex-row-reverse">
        <input type="text" v-model="param.keyword" />
      </div>
      <button @click="getTripList">검색</button>
    </div>
    <VKakaoMap :stations="tripList" :selectStation="selectStation" />
    <div class="row" style="width: 100%; height: 400px; overflow: auto">
      <table class="table table-hover" style="width: 100%">
        <thead>
          <tr class="text-center">
            <th scope="col">대표이미지</th>
            <th scope="col">관광지명</th>
            <th scope="col">주소</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="text-center"
            v-for="trip in tripList"
            :key="trip.contentId"
            @click="viewStation(trip)">
            <td style="width: 100px">
              <img :src="trip.firstImage" />
            </td>
            <td style="width: 200px">{{ trip.title }}</td>
            <td style="width: 300px">{{ trip.addr1 }} {{ trip.addr2 }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped></style>
