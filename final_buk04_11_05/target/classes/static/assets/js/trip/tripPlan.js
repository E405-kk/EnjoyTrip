// index page 로딩 후 전국의 시도 설정.
let areaUrl =
	"https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
	serviceKey +
	"&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";



// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", async () => {
	hideMarkers();

	let queryString = `serviceKey=${serviceKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
	let areaCode = document.getElementById("search-area").value;

	// 관광지 유형
	let contentTypeId = document.getElementById("search-content-id").value;
	let keyword = document.getElementById("search-keyword").value;
	if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;

	let base = "http://localhost:8080/trip/trip?action=tripSearch&sidoCode=" + areaCode + "&contentTypeId=" + contentTypeId;
	if (keyword) {
		base += `&keyword=` + keyword;
	}

	await fetch(base)
		.then((response) => response.json())
		.then((data) => makeList(data));

});

var tripPlanList = [];
var tripPlanPosList = [];
var positions; // marker 배열.

var contentTypes; // 관광지 유형을 저장
var tripsSaved;
function makeList(data) {
	document.querySelector("table").setAttribute("style", "display: ;");
	let trips = data;
	console.log(trips);
	tripsSaved = trips;
	let tripList = ``;
	positions = [];
	contentTypes = [];
	trips.forEach((area) => {
		// console.log(area.contenttypeid);
		contentTypes.push(area.contentTypeId);
		tripList += `
          <tr onclick="moveCenter(${area.latitude}, ${area.longitude});">
            <td><img src="${area.firstImage}" width="100px"></td>
            <td>${area.title}</td>
            <td>${area.addr1} ${area.addr2}</td>
            <td>${area.latitude}</td>
            <td>${area.longitude}</td>
          </tr>
        `;

		let markerInfo = {
			title: area.title,
			latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
		};
		positions.push(markerInfo);
	});
	console.log(positions);
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

		var imgsrc = "./assets/img/img_marker";
		imgsrc += contentTypes[i];
		imgsrc += ".png";
		//var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		var markerImageTmp = new kakao.maps.MarkerImage(imgsrc, imageSize);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			// map: map, // 마커를 표시할 지도  클러스터링위한  주석
			position: positions[i].latlng, // 마커를 표시할 위치
			title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			image: markerImageTmp, // 마커 이미지
		});

		// 커스텀 오버레이 설정@@@@@@
		var img = tripsSaved[i].firstImage;
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
		});
		markers.push(marker); // 동규
		infowindows.push(infowindow);
	}

	for (let i = 0; i < positions.length; i++) {
		// 마커에 마우스오버 이벤트를 등록합니다
		kakao.maps.event.addListener(markers[i], "mouseover", function() {
			// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
			infowindows[i].open(map, markers[i]);
		});

		// 마커에 마우스아웃 이벤트를 등록합니다
		kakao.maps.event.addListener(markers[i], "mouseout", function() {
			// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
			infowindows[i].close();
		});

		kakao.maps.event.addListener(markers[i], "click", function() {
			console.log("마커 클릭!");
			let pos = markers[i].getPosition();
			console.log(pos);
			// 여행 계획 마커를 생성합니다

			let markerImageTmp = new kakao.maps.MarkerImage(
				"./assets/img/plane.png",
				imageSize
			);

			let marker = new kakao.maps.Marker({
				map: map, // 마커를 표시할 지도  클러스터링위한  주석
				position: pos, // 마커를 표시할 위치
				title: pos.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				image: markerImageTmp, // 마커 이미지
			});
			tripPlanList.push(marker);
			tripPlanPosList.push(pos);
			marker.setMap(map);


			addAnswer(tripsSaved[i].title, marker);
			reloadPlanLine();

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


let addAnswer = function(title, marker) {
	// 리스트
	let listDiv = document.getElementById("plan-list");

	//추가할 노드
	let divEl = document.createElement("div");
	divEl.setAttribute("class", "row mb-1 plan-list-item");
	let divEl2 = document.createElement("div");
	divEl2.setAttribute("class", "col-md-10 d-flex");

	//인풋 추가
	let inputEl = document.createElement("input");
	inputEl.setAttribute("name", "plan-list-item")
	inputEl.setAttribute("type", "text");
	inputEl.setAttribute("class", "form-control");
	inputEl.setAttribute("value", title);
	inputEl.setAttribute("readonly", "readonly");

	//삭제 버튼 추가
	let btnEl = document.createElement("button");
	btnEl.setAttribute("type", "button");
	btnEl.setAttribute("class", "btn btn-outline-danger btn-sm");
	btnEl.appendChild(document.createTextNode("삭제"));

	btnEl.addEventListener("click", function() {
		console.log(this);
		let parent = this.parentNode;
		listDiv.removeChild(parent.parentNode);

		// 리스트 에서 마커 삭제
		for (var i = 0; i < tripPlanList.length; i++) {
			if (tripPlanList[i] === marker) {
				tripPlanList[i].setMap(null);
				tripPlanList.splice(i, 1);
				tripPlanPosList.splice(i, 1);
				i--;
			}
		}

		reloadPlanLine();

	});

	//위 버튼 추가
	let btnEl2 = document.createElement("button");
	btnEl2.setAttribute("type", "button");
	btnEl2.setAttribute("class", "btn btn-outline-danger btn-sm");
	btnEl2.appendChild(document.createTextNode("위"));

	btnEl2.addEventListener("click", function() {

		console.log("위로 클릭!");

		// 리스트 에서 마커 이동
		for (var i = 0; i < tripPlanList.length; i++) {
			if (tripPlanList[i] === marker) {

				if (i == 0) {
					return;
				}

				//DOM 변경
				let target = this.parentNode.parentNode;
				console.log(target);

				let listDiv = document.getElementById("plan-list");
				console.log(listDiv);

				// let prevSibling = target.previousSibling;
				// console.log(prevSibling);
				listDiv.insertBefore(target, target.previousSibling);
				console.log(listDiv);

				// 마커 변경
				let tmp = tripPlanList[i - 1];
				tripPlanList[i - 1] = tripPlanList[i];
				tripPlanList[i] = tmp;

				let tmp2 = tripPlanPosList[i - 1];
				tripPlanPosList[i - 1] = tripPlanPosList[i];
				tripPlanPosList[i] = tmp2;

				break;
			}
		}


		reloadPlanLine();

	});


	//아래 버튼 추가
	let btnEl3 = document.createElement("button");
	btnEl3.setAttribute("type", "button");
	btnEl3.setAttribute("class", "btn btn-outline-danger btn-sm");
	btnEl3.appendChild(document.createTextNode("아래"));
	btnEl3.addEventListener("click", function() {

		console.log("아래 클릭!");

		console.log(tripPlanList);
		console.log(tripPlanPosList);

		// 리스트 에서 마커 이동
		for (var i = 0; i < tripPlanList.length; i++) {
			if (tripPlanList[i] === marker) {

				if (i == tripPlanList.length - 1) {
					return;
				}

				//DOM 변경
				let target = this.parentNode.parentNode.nextSibling;
				console.log(target);

				let listDiv = document.getElementById("plan-list");

				// let prevSibling = target.previousSibling;
				// console.log(prevSibling);
				listDiv.insertBefore(target, target.previousSibling);


				//마커 변경
				let tmp = tripPlanList[i + 1];
				tripPlanList[i + 1] = tripPlanList[i];
				tripPlanList[i] = tmp;

				let tmp2 = tripPlanPosList[i + 1];
				tripPlanPosList[i + 1] = tripPlanPosList[i];
				tripPlanPosList[i] = tmp2;

				break;
			}
		}

		console.log(tripPlanList);
		console.log(tripPlanPosList);

		reloadPlanLine();

	});


	divEl2.appendChild(inputEl);
	divEl2.appendChild(btnEl);
	divEl2.appendChild(btnEl2);
	divEl2.appendChild(btnEl3);

	divEl.appendChild(divEl2);
	listDiv.appendChild(divEl);
};

var polyline = null;


function reloadPlanLine() {
	if (polyline != null) {
		polyline.setMap(null);
	}

	console.log(tripPlanPosList);
	// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
	var linePath = [];
	for (let i = 0; i < tripPlanPosList.length; i++) {
		//좌표 찍기
		linePath.push(tripPlanPosList[i]);
	}

	// 지도에 표시할 선을 생성합니다
	polyline = new kakao.maps.Polyline({
		path: linePath, // 선을 구성하는 좌표배열 입니다
		strokeWeight: 5, // 선의 두께 입니다
		strokeColor: '#2D64F5', // 선의 색깔입니다
		strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle: 'solid' // 선의 스타일입니다
	});
	// 지도에 선을 표시합니다 
	polyline.setMap(map);
}


document.getElementById("tripPlanSaveBtn").addEventListener("click", async () => {


	let list = document.getElementsByName("plan-list-item");


	// 순서, 이름, 좌표를 준다.

	let names = [];

	for (let i = 0; i < list.length; i++) {
		let name = list[i].getAttribute("value");
		console.log(name);
		names.push(name);
	}
	console.log(names);

	await fetch("http://localhost:8080/trip/trip?action=tripPlanSave", {
		method: "POST",
		body: JSON.stringify({
			names,
		}),
	})
		.then((response) => response.json())
		.then((data) => {
			alert(data);
			if (data == "저장 완료!") {
				console.log("저장완료----")
				window.location.href = 'http://localhost:8080/trip/trip?action=mvUserTripPlan';
			}
		});






});


