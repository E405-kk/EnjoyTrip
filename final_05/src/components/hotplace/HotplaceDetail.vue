<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/hotplace";
import VArticle from "@/components/common/VArticle.vue";
const userId = sessionStorage.getItem("userId");

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const article = ref({});

onMounted(() => {
  getArticle();
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

function moveList() {
  router.push({ name: "hotplace-list" });
}

function moveModify() {
  router.push({ name: "hotplace-modify", params: { articleno } });
}

function onDeleteArticle() {
  deleteArticle(
    articleno,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
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
          <button
            type="button"
            class="bg-gray-100 text-gray-700 font-semibold py-2 px-4 border border-gray-300 rounded-md shadow-sm hover:bg-gray-200 hover:text-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
            @click="moveList">
            글목록
          </button>
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
</template>

<style scoped></style>
