<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  detailArticle,
  deleteArticle,
  checkGood,
  updateGood,
} from "@/api/hotplace";
import Swal from "sweetalert2";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const userId = userInfo.value.userId;

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const article = ref({});
const isGood = ref(false);
const param = ref({
  userId: userId,
  articleNo: articleno,
  check: "",
});
var map;
var marker;
const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(36.386120817951436, 128.02537185582057), // 지도의 중심좌표
    level: 8, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(container, options);
  // 지도를 클릭한 위치에 표출할 마커입니다
  marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter(),
  });

  // 지도에 마커를 표시합니다
  marker.setMap(map);
};
watch(article, (newVal) => {
  if (newVal.latitude && newVal.longitude) {
    const position = new kakao.maps.LatLng(newVal.latitude, newVal.longitude);
    map.setCenter(position);
    marker.setPosition(position);
  }
});
onMounted(() => {
  getArticle();
  checkIsGood();
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

const getArticle = () => {
  detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

const checkIsGood = () => {
  checkGood(
    param.value,
    ({ data }) => {
      isGood.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};
const updateIsGood = () => {
  if (!isGood.value) {
    param.value.check = "plus";
  } else {
    param.value.check = "";
  }
  console.log(param.value);
  updateGood(
    param.value,
    () => {
      isGood.value = !isGood.value;
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "hotplace-list" });
}

function moveModify() {
  router.push({ name: "hotplace-modify", params: { articleno } });
}

function onDeleteArticle() {
  Swal.fire({
    title: "게시글을 삭제하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#848484",
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      deleteArticle(
        articleno,
        (response) => {
          let msg = response.data;
          if (response.status == 200) {
            msg = "게시글이 삭제되었습니다.";
            Swal.fire({
              title: "삭제",
              text: msg,
              icon: "success",
            });
            moveList();
          } else {
            Swal.fire({
              icon: "error",
              text: msg,
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  });
}
function getImageUrl() {
  if (article.value.fileInfo == null) {
    return null;
  }
  if (article.value.fileInfo.saveFile) {
    var url = "/src/assets/upload/";
    url +=
      article.value.fileInfo.saveFolder + "/" + article.value.fileInfo.saveFile;
    return url;
  } else {
    return "/src/assets/about-bg.jpg";
  }
}
</script>

<template>
  <div class="container mx-auto p-5">
    <div id="map" class="mx-auto" style="width: 80%; height: 300px"></div>
    <div class="flex justify-center mx-auto" style="width: 80%">
      <div style="width: 60%; height: auto; aspect-ratio: 3/4; max-width: 100%">
        <img
          :src="getImageUrl()"
          class="max-w-full h-auto p-5"
          style="width: 100%; height: 100%; object-fit: cover" />
      </div>
      <div class="w-full text-start bg-white shadow-md rounded-lg p-5">
        <div class="flex">
          <div class="flex-1 space-y-4">
            <div>
              <label for="subject" class="text-lx">제목:</label>
              <div
                id="subject"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full">
                {{ article.subject }}
              </div>
            </div>
            <div>
              <label for="addr" class="text-lx">주소:</label>
              <div
                id="addr"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full">
                {{ article.addr }}
              </div>
            </div>
            <div>
              <label for="visitDate" class="text-lx">방문 날짜:</label>
              <div
                id="visitDate"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full">
                {{ article.visitDate }}
              </div>
            </div>
            <div>
              <label for="content" class="block mb-2 text-lg">내용:</label>
              <div
                id="content"
                class="w-full p-4 text-gray-600 bg-emerald-50 outline-none rounded-md"
                style="height: 200px">
                {{ article.content }}
              </div>
            </div>
          </div>
        </div>
        <div class="border-t my-3"></div>
        <div class="flex justify-between mt-5">
          <div class="flex">
            <button
              type="button"
              class="bg-gray-100 text-gray-700 font-semibold py-2 px-4 mr-3 border border-gray-300 rounded-md shadow-sm hover:bg-gray-200 hover:text-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
              @click="moveList">
              글목록
            </button>
            <template v-if="userId">
              <button
                v-if="isGood"
                @click="updateIsGood"
                class="middle none center mr-3 flex items-center justify-center rounded-lg bg-gradient-to-tr from-pink-600 to-pink-400 p-3 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                data-ripple-light="true">
                <i class="fas fa-heart text-lg leading-none"></i>
              </button>
              <button
                v-if="!isGood"
                @click="updateIsGood"
                class="middle none center mr-3 flex items-center justify-center rounded-lg border border-pink-500 p-3 font-sans text-xs font-bold uppercase text-pink-500 transition-all hover:opacity-75 focus:ring focus:ring-pink-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                data-ripple-dark="true">
                <i class="fas fa-heart text-lg leading-none"></i>
              </button>
            </template>
          </div>
          <div class="flex">
            <button
              v-if="userId === article.userId"
              type="button"
              class="mr-3 bg-emerald-100 text-emerald-700 font-semibold py-2 px-4 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50"
              @click="moveModify">
              글수정
            </button>
            <button
              v-if="userId === article.userId"
              type="button"
              class="bg-red-100 text-red-700 font-semibold py-2 px-4 border border-red-300 rounded-md shadow-sm hover:bg-red-200 hover:text-red-800 focus:outline-none focus:ring-2 focus:ring-red-300 focus:ring-opacity-50"
              @click="onDeleteArticle">
              글삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
    integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
    crossorigin="anonymous" />
</template>

<style scoped></style>
