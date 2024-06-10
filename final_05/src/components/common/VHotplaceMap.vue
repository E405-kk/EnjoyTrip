<script setup>
import { onMounted } from "vue";
const emit = defineEmits(["pick-place"]);
var map;
var geocoder;
var latlng;
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
    center: new kakao.maps.LatLng(36.386120817951436, 128.02537185582057), // 지도의 중심좌표
    level: 12, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(container, options);
  // 주소-좌표 변환 객체를 생성합니다
  geocoder = new kakao.maps.services.Geocoder();
  // 지도를 클릭한 위치에 표출할 마커입니다
  var marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter(),
  });

  // 지도에 마커를 표시합니다
  marker.setMap(map);
  // 지도에 클릭 이벤트를 등록합니다
  // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
  kakao.maps.event.addListener(map, "click", function (mouseEvent) {
    // 클릭한 위도, 경도 정보를 가져옵니다
    latlng = mouseEvent.latLng;

    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);

    searchDetailAddrFromCoords(latlng, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        var detailAddr = result[0].address.address_name;

        // 마커를 클릭한 위치에 표시합니다
        marker.setMap(map);
        emit("pick-place", latlng, detailAddr);
      }
    });
  });
};
function searchDetailAddrFromCoords(coords, callback) {
  // 좌표로 법정동 상세 주소 정보를 요청합니다
  geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}
</script>

<template>
  <div id="map" style="width: 100%; height: 750px" class="rounded-lg"></div>
</template>

<style scoped></style>
