<script setup>
import { ref, watch, onMounted, toRaw } from "vue";

var map;
var clusterer;
const positions = ref([]);
const markers = ref([]);
const infowindows = ref([]);
const tripPlanList = ref([]);
const tripPlanPosList = ref([]);
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
      obj.contentId = station.contentId;
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
  // 마커를 생성합니다
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

    // 마커에 마우스아웃 이벤트를 등록합니다
    kakao.maps.event.addListener(markers.value[i], "mouseout", function () {
      // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
      infowindows.value[i].close();
    });

    kakao.maps.event.addListener(markers.value[i], "click", function () {
      infowindows.value[i].close();
      let pos = markers.value[i].getPosition();
      // 여행 계획 마커를 생성합니다

      let markerImageTmp = new kakao.maps.MarkerImage(
        "/src/assets/plane.png",
        imageSize
      );
      let marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도  클러스터링위한  주석
        position: pos, // 마커를 표시할 위치
        title: pos.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImageTmp, // 마커 이미지
      });
      tripPlanList.value.push(marker);
      tripPlanPosList.value.push(pos);
      marker.setMap(map);
      addAnswer(positions.value[i].title, positions.value[i].contentId, marker);
      reloadPlanLine();
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

let addAnswer = function (title, contentId, marker) {
  // 리스트
  let listDiv = document.getElementById("plan-list");

  //추가할 노드
  let divEl = document.createElement("div");
  divEl.setAttribute("class", "row mb-1 plan-list-item border rounded-md p-2");
  let divEl2 = document.createElement("div");
  divEl2.setAttribute("class", "col-md-10 flex place-items-center");

  //인풋 추가
  let inputEl = document.createElement("input");
  inputEl.setAttribute("name", "plan-list-item");
  inputEl.setAttribute("type", "text");
  inputEl.setAttribute("class", "ml-2 form-control outline-none");
  inputEl.setAttribute("value", title);
  inputEl.setAttribute("data-content-id", contentId);
  inputEl.setAttribute("readonly", "readonly");

  //삭제 버튼 추가
  let btnEl = document.createElement("button");
  btnEl.setAttribute("type", "button");
  btnEl.setAttribute(
    "class",
    "rounded-xl bg-red-500 px-2 py-1 text-base font-medium text-white transition duration-200 hover:bg-red-400 active:bg-red-600"
  );
  btnEl.appendChild(document.createTextNode("삭제"));

  btnEl.addEventListener("click", function () {
    let parent = this.parentNode;
    listDiv.removeChild(parent.parentNode);
    // 리스트 에서 마커 삭제
    for (var i = 0; i < tripPlanList.value.length; i++) {
      if (toRaw(tripPlanList.value[i]) === marker) {
        tripPlanList.value[i].setMap(null);
        tripPlanList.value.splice(i, 1);
        tripPlanPosList.value.splice(i, 1);
        i--;
      }
    }

    reloadPlanLine();
  });

  //위 버튼 추가
  let btnEl2 = document.createElement("button");
  btnEl2.setAttribute("type", "button");
  btnEl2.setAttribute(
    "class",
    "ml-auto mr-2 rounded-xl bg-white px-2 py-1 text-base font-medium text-indigo-600  transition duration-200 hover:text-white hover:bg-indigo-500 active:bg-indigo-600 border border-indigo-600"
  );
  btnEl2.setAttribute("style", "transform: rotate(90deg)");
  btnEl2.appendChild(document.createTextNode("<"));

  btnEl2.addEventListener("click", function () {
    // 리스트 에서 마커 이동
    for (var i = 0; i < tripPlanList.value.length; i++) {
      if (toRaw(tripPlanList.value[i]) === marker) {
        if (i == 0) {
          return;
        }

        //DOM 변경
        let target = this.parentNode.parentNode;

        let listDiv = document.getElementById("plan-list");

        listDiv.insertBefore(target, target.previousSibling);

        // 마커 변경
        let tmp = tripPlanList.value[i - 1];
        tripPlanList.value[i - 1] = tripPlanList.value[i];
        tripPlanList.value[i] = tmp;

        let tmp2 = tripPlanPosList.value[i - 1];
        tripPlanPosList.value[i - 1] = tripPlanPosList.value[i];
        tripPlanPosList.value[i] = tmp2;

        break;
      }
    }

    reloadPlanLine();
  });

  //아래 버튼 추가
  let btnEl3 = document.createElement("button");
  btnEl3.setAttribute("type", "button");
  btnEl3.setAttribute(
    "class",
    "mr-2 rounded-xl bg-white px-2 py-1 text-base font-medium text-indigo-600 transition duration-200 hover:text-white hover:bg-indigo-500 active:bg-indigo-600 border border-indigo-600"
  );
  btnEl3.setAttribute("style", "transform: rotate(90deg)");
  btnEl3.appendChild(document.createTextNode(">"));
  btnEl3.addEventListener("click", function () {
    // 리스트 에서 마커 이동
    for (var i = 0; i < tripPlanList.value.length; i++) {
      if (toRaw(tripPlanList.value[i]) === marker) {
        if (i == tripPlanList.value.length - 1) {
          return;
        }

        //DOM 변경
        let target = this.parentNode.parentNode.nextSibling;

        let listDiv = document.getElementById("plan-list");

        listDiv.insertBefore(target, target.previousSibling);

        //마커 변경
        let tmp = tripPlanList.value[i + 1];
        tripPlanList.value[i + 1] = tripPlanList.value[i];
        tripPlanList.value[i] = tmp;

        let tmp2 = tripPlanPosList.value[i + 1];
        tripPlanPosList.value[i + 1] = tripPlanPosList.value[i];
        tripPlanPosList.value[i] = tmp2;

        break;
      }
    }

    reloadPlanLine();
  });

  divEl2.appendChild(inputEl);
  divEl2.appendChild(btnEl2);
  divEl2.appendChild(btnEl3);
  divEl2.appendChild(btnEl);
  divEl.appendChild(divEl2);
  listDiv.appendChild(divEl);
};

var polyline = null;

function reloadPlanLine() {
  if (polyline != null) {
    polyline.setMap(null);
  }

  // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
  const linePath = ref([]);
  for (let i = 0; i < tripPlanPosList.value.length; i++) {
    //좌표 찍기
    linePath.value.push(tripPlanPosList.value[i]);
  }

  // 지도에 표시할 선을 생성합니다
  polyline = new kakao.maps.Polyline({
    path: linePath.value, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#2D64F5", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });
  // 지도에 선을 표시합니다
  polyline.setMap(map);
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
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 350px;
  height: auto;
  margin-left: -144px;
  text-align: left;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 350px;
  height: 400px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  background: #fff;
  overflow: auto;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
  margin-bottom: 5px;
}
.info .title {
  padding: 5px 0 30px 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  /* text-overflow: ellipsis; */
  white-space: normal;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: auto;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}

.overview {
  padding-right: 10px;
  white-space: normal;
  overflow: auto;
}
</style>
