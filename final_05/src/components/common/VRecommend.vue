<script setup>
import { detailMonthly, detailRank } from "@/api/map";
import { onMounted, ref, watch } from "vue";
const props = defineProps({
  idx: Number,
  type: String,
});
const recommend = ref({});
let map;
let marker;

// recommend 객체의 변경 사항을 감지하여 지도 업데이트
watch(recommend, (newVal) => {
  if (newVal.latitude && newVal.longitude) {
    const position = new kakao.maps.LatLng(newVal.latitude, newVal.longitude);
    map.setCenter(position);
    marker.setPosition(position);
  }
});
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
  if (props.type == "rank") {
    getTrip();
  } else {
    getRecommend();
  }
});

const getRecommend = () => {
  detailMonthly(
    Number(props.idx),
    ({ data }) => {
      recommend.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

const getTrip = () => {
  detailRank(
    Number(props.idx),
    ({ data }) => {
      recommend.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};
const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);
  // 마커가 표시될 위치입니다
  const mapPosition = new kakao.maps.LatLng(33.450701, 126.570667);

  // 마커를 생성합니다
  marker = new kakao.maps.Marker({
    position: mapPosition,
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);
};
</script>

<template>
  <div
    class="w-full mx-auto my-10 lg:w-10/12 text-center bg-white shadow-md rounded-lg p-5">
    <div class="flex">
      <div
        class="w-5/12 mr-3 rounded-lg"
        id="map"
        style="width: 100%; height: 500px"></div>

      <img
        :src="recommend.firstImage"
        class="w-7/12 rounded-lg"
        style="height: 500px" />
    </div>
    <div class="mt-5">
      <div class="text-center text-3xl font-bold mb-3">
        {{ recommend.title }}
      </div>
      <div class="text-center text-xl font-bold mb-3">
        {{ recommend.addr1 }} {{ recommend.addr2 }}
      </div>
      <div class="text-left text-lg border-t pt-3">
        {{ recommend.overview }}
      </div>
    </div>
  </div>
</template>

<style scoped></style>
