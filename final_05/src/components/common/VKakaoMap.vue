<script setup>
import { ref, watch, onMounted } from "vue";
var map;
var clusterer;
const positions = ref([]);
const markers = ref([]);
const infowindows = ref([]);
const props = defineProps({ stations: Array, selectStation: Object });
watch(
  () => props.selectStation.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(
      props.selectStation.latitude,
      props.selectStation.longitude
    );

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

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

watch(
  () => props.stations.value,
  () => {
    positions.value = [];
    props.stations.forEach((station) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(station.latitude, station.longitude);
      obj.title = station.title;
      obj.contentTypeId = station.contentTypeId;
      obj.firstImage = station.firstImage;
      obj.addr1 = station.addr1;
      obj.addr2 = station.addr2;
      obj.zipcode = station.zipcode;
      obj.tel = station.tel;
      obj.overview = station.overview;
      positions.value.push(obj);
    });
    loadMarkers();
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);
  clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
    averageCenter: false, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
    minLevel: 7, // 클러스터 할 최소 지도 레벨
  });
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();
  hiderMarksersInCluster(clusterer);
  const imageSize = new kakao.maps.Size(25, 25);
  markers.value = [];
  infowindows.value = [];

  positions.value.forEach((position) => {
    // 마커 이미지를 생성합니다

    var imgsrc = "/src/assets/img_marker";
    imgsrc += position.contentTypeId;
    imgsrc += ".png";
    const markerImageTmp = new kakao.maps.MarkerImage(imgsrc, imageSize);
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      image: markerImageTmp, // 마커의 이미지
    });
    var img = position.firstImage;
    if (img == "") {
      img = "/src/assets/bg-white.png";
    }
    var contentVal =
      '        <div class="mb-10" style="padding: 5px;">' +
      `           <div> ${position.title} </div>` +
      `           <img src="${img}" class="m-auto" width="150px" height="auto"/>` +
      `        <div>${position.addr1}</div> </div>`;
    var infowindow = new kakao.maps.InfoWindow({
      content: contentVal,
    });
    infowindows.value.push(infowindow);
    markers.value.push(marker);
  });
  for (let i = 0; i < positions.value.length; i++) {
    // 마커에 마우스오버 이벤트를 등록합니다
    kakao.maps.event.addListener(markers.value[i], "mouseover", function () {
      // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
      infowindows.value[i].open(map, markers.value[i]);
    });

    // // 마커에 마우스아웃 이벤트를 등록합니다
    kakao.maps.event.addListener(markers.value[i], "mouseout", function () {
      // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
      infowindows.value[i].close();
    });

    kakao.maps.event.addListener(markers.value[i], "click", function () {
      infowindows.value[i].close();

      var content =
        `    <a href="#">` +
        `       <h5 class="text-gray-900 font-bold text-2xl tracking-tight mb-2 dark:text-white">${positions.value[i].title}</h5>` +
        `   </a>` +
        `<div>${positions.value[i].addr1}</div>` +
        `   <p class="font-normal text-gray-700 mb-3 dark:text-gray-400 overflow-content ">${positions.value[i].overview}</p>` +
        `   <a href="https://search.naver.com/search.naver?&query=${positions.value[i].title}" target="_blank" class="text-white bg-green-600 hover:bg-green-500 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-3 py-2 text-center inline-flex items-center  dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">` +
        `       Read more` +
        `       <svg class="-mr-1 ml-2 h-4 w-4" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>` +
        `   </a>`;

      var customOverlay = new kakao.maps.CustomOverlay({
        position: markers.value[i].n,
        map: map,
      });

      clusterer.clear();
      var div1 = document.createElement("div");
      div1.setAttribute(
        "class",
        "scrollbar custom-overlay z-100 bg-white shadow-md border border-gray-200 rounded-lg max-w-sm dark:bg-gray-800 dark:border-gray-700"
      );

      var img = document.createElement("img");
      img.setAttribute("class", "rounded-t-lg");
      img.setAttribute("src", `${positions.value[i].firstImage}`);

      var div2 = document.createElement("div");
      div2.setAttribute("class", "p-5");

      var div4 = document.createElement("div");
      div4.innerHTML = content;
      div4.setAttribute("class", "body");

      var closeBtn = document.createElement("div");
      closeBtn.setAttribute("class", "close");

      closeBtn.onclick = function () {
        customOverlay.setMap(null);
        loadMarkers();
      };
      div1.appendChild(img);
      div1.appendChild(div2);
      div2.appendChild(closeBtn);
      div2.appendChild(div4);

      customOverlay.setContent(div1);
      customOverlay.setMap(map);
      div1.addEventListener("wheel", function (event) {
        event.stopPropagation(); // 이벤트 전파를 중단하여 맵의 기본 동작을 막음
      });
    });
  }
  clusterer.addMarkers(markers.value);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};
function hiderMarksersInCluster(clusterer) {
  clusterer.clear();
}
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 600px;
  border-radius: 20px;
}

.custom-overlay {
  background: white;
  border: 1px solid gray;
  border-radius: 8px;
  width: 350px; /* Increase the width to make the overlay wider */
  max-height: 400px;
  overflow-y: auto;
  overflow-x: hidden;
  position: relative;
  word-break: break-word;
  white-space: pre-wrap;
}

.custom-overlay .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}

.custom-overlay .close:hover {
  cursor: pointer;
}

.overview-content {
  max-height: 200px;
  overflow-y: auto;
  overflow-x: hidden;
  word-break: break-word;
  white-space: pre-wrap;
}
</style>
