// index page 로딩 후 전국의 시도 설정.
let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

function makeOption(data) {
  let areas = data.response.body.items.item;
  // console.log(areas);
  let sel = document.getElementById("search-area");
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));

    sel.appendChild(opt);
  });
}

// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", () => {
  hideMarkers();

  let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
  // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

  let queryString = `serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = document.getElementById("search-area").value;

  // 관광지 유형
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;
  if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;

  // console.log(parseInt(contentTypeId));
  if (parseInt(contentTypeId) != 0)
    queryString += `&contentTypeId=${contentTypeId}`;
  // if (!keyword) {
  //   alert("검색어 입력 필수!!!");
  //   return;
  // } else searchUrl += `&keyword=${keyword}`;
  let service = ``;
  if (keyword) {
    service = `searchKeyword1`;
    queryString += `&keyword=${keyword}`;
  } else {
    service = `areaBasedList1`;
  }
  let searchUrl = baseUrl + service + "?" + queryString;

  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeList(data));
});

var tripPlanList = [];
var positions; // marker 배열.

var contentTypes; // 관광지 유형을 저장
var tripsSaved;
function makeList(data) {
  document.querySelector("table").setAttribute("style", "display: ;");
  let trips = data.response.body.items.item;
  tripsSaved = trips;
  console.log(trips);
  let tripList = ``;
  positions = [];
  contentTypes = [];
  trips.forEach((area) => {
    // console.log(area.contenttypeid);
    contentTypes.push(area.contenttypeid);
    tripList += `
          <tr onclick="moveCenter(${area.mapy}, ${area.mapx});">
            <td><img src="${area.firstimage}" width="100px"></td>
            <td>${area.title}</td>
            <td>${area.addr1} ${area.addr2}</td>
            <td>${area.mapy}</td>
            <td>${area.mapx}</td>
          </tr>
        `;

    let markerInfo = {
      title: area.title,
      latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
    };
    positions.push(markerInfo);
  });
  document.getElementById("trip-list").innerHTML = tripList;
  displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 7, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

var clusterer = new kakao.maps.MarkerClusterer({
  map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
  averageCenter: false, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
  minLevel: 10, // 클러스터 할 최소 지도 레벨
});

var markers = []; // 동규
function displayMarker() {
  // hideMarkers();
  //클러스터 마커 초기화
  hiderMarksersInCluster(clusterer);

  markers = [];
  infowindows = [];
  // 마커 이미지의 이미지 주소입니다
  var imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(25, 25);

    // 마커 이미지를 생성합니다

    var imgsrc = "../assets/img/img_marker";
    imgsrc += contentTypes[i];
    imgsrc += ".png";
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    var markerImageTmp = new kakao.maps.MarkerImage(imgsrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      // map: map, // 마커를 표시할 지도  클러스터링위한  주석
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImageTmp, // 마커 이미지
    });

    // 커스텀 오버레이 설정@@@@@@
    var img = tripsSaved[i].firstimage;
    if (img == "") {
      img = "../assets/img/about-bg.jpg";
    }

    var zipcode = tripsSaved[i].zipcode;
    if (!zipcode) {
      zipcode = "";
    }
    var contentVal =
      '        <div class="title" >' +
      `            ${tripsSaved[i].title}` +
      "        </div>" +
      '        <div class="body d-flex">' +
      `                <img src="${img}"  class = "col-3" width = "50px" height ="50px">` +
      `                <div class = "col-9">${tripsSaved[i].addr1}  <div style="color:gray">(우)${zipcode}</div>
            <div style = "color:gray">(전화번호)${tripsSaved[i].tel}</div>
            ` +
      "        </div>";

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
      content: contentVal,
      removable: true //인포윈도우를 닫을 수 있는 x버튼 표시
    });
    markers.push(marker); // 동규
    infowindows.push(infowindow);
  }

  for (let i = 0; i < positions.length; i++) {
    // // 마커에 마우스오버 이벤트를 등록합니다
    // kakao.maps.event.addListener(markers[i], "mouseover", function () {
    //   // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    //   infowindows[i].open(map, markers[i]);
    // });

    // // 마커에 마우스아웃 이벤트를 등록합니다
    // kakao.maps.event.addListener(markers[i], "mouseout", function () {
    //   // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    //   infowindows[i].close();
    // });

    kakao.maps.event.addListener(markers[i], "click", function () {

      for(let i =0;i<infowindows.length;i++){
        infowindows[i].close();
      }

      infowindows[i].open(map, markers[i]);
      // console.log("마커 클릭!");
      // let pos = markers[i].getPosition();
      // console.log(pos);
      // // 여행 계획 마커를 생성합니다

      // let markerImageTmp = new kakao.maps.MarkerImage(
      //   "../assets/img/plane.png",
      //   imageSize
      // );

      // let marker = new kakao.maps.Marker({
      //   map: map, // 마커를 표시할 지도  클러스터링위한  주석
      //   position: pos, // 마커를 표시할 위치
      //   title: pos.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      //   image: markerImageTmp, // 마커 이미지
      // });
      // tripPlanList.push(marker);
      // marker.setMap(map);
    });
  }

  clusterer.addMarkers(markers);

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng));
}

// @@@ 마커 삭제

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
  setMarkers(map);
}

// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
  setMarkers(null);
}

function hiderMarksersInCluster(clusterer) {
  clusterer.clear();
}