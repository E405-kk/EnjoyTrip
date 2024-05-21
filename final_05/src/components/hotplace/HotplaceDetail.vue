<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  detailArticle,
  deleteArticle,
  checkGood,
  updateGood,
} from "@/api/hotplace";
import VArticle from "@/components/common/VArticle.vue";
import Swal from "sweetalert2";
const userId = sessionStorage.getItem("userId");

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const article = ref({});
const isGood = ref(false);
const param = ref({
  userId: userId,
  articleNo: articleno,
  check: isGood.value,
});

onMounted(() => {
  getArticle();
  checkIsGood();
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
const updateGood = () => {
  updateGood(
    param.value,
    (response) => {
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
function getImageUrl(article) {
  if (article && article.fileInfo) {
    return (
      "/src/assets/upload/" +
      article.fileInfo.saveFolder +
      "/" +
      article.fileInfo.saveFile
    );
  } else {
    return "";
  }
}
</script>

<template>
  <div class="container mx-auto p-5">
    <div class="flex justify-center">
      <img
        :src="getImageUrl(article)"
        class="max-w-full h-auto"
        style="width: 40%; max-width: 40%; height: auto" />
      <div
        class="w-full lg:w-10/12 text-start bg-white shadow-md rounded-lg p-5">
        <VArticle :article="article" />
        <div class="border-t my-3"></div>
        <div class="flex justify-between mt-10">
          <div class="flex">
            <button
              type="button"
              class="bg-gray-100 text-gray-700 font-semibold py-2 px-4 mr-3 border border-gray-300 rounded-md shadow-sm hover:bg-gray-200 hover:text-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
              @click="moveList">
              글목록
            </button>
            <button
              v-if="isGood"
              @click="updateGood"
              class="middle none center mr-3 flex items-center justify-center rounded-lg bg-gradient-to-tr from-pink-600 to-pink-400 p-3 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
              data-ripple-light="true">
              <i class="fas fa-heart text-lg leading-none"></i>
            </button>
            <button
              v-if="!isGood"
              @click="updateGood"
              class="middle none center mr-3 flex items-center justify-center rounded-lg border border-pink-500 p-3 font-sans text-xs font-bold uppercase text-pink-500 transition-all hover:opacity-75 focus:ring focus:ring-pink-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
              data-ripple-dark="true">
              <i class="fas fa-heart text-lg leading-none"></i>
            </button>
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
