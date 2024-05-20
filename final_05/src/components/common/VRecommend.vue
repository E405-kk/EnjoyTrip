<script setup>
import { onMounted } from "vue";
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
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);
  // 마커가 표시될 위치입니다
  const markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

  // 마커를 생성합니다
  const marker = new kakao.maps.Marker({
    position: markerPosition,
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.value.setMap(map);
};
</script>

<template>
  <div class="w-full lg:w-10/12 text-start bg-white shadow-md rounded-lg p-5">
    <div id="map" style="width: 100%; height: 350px"></div>
  </div>
</template>

<style scoped></style>
