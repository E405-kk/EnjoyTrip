<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";

const route = useRoute();
const router = useRouter();

// const articleno = ref(route.params.articleno);
const { articleno } = route.params;

const article = ref({});

onMounted(() => {
  getArticle();
});

const getArticle = () => {
  // const { articleno } = route.params;
  console.log(articleno + "번글 얻으러 가자!!!");
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
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(articleno + "번글 삭제하러 가자!!!");
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
</script>

<template>
  <div class="container mx-auto">
    <div class="flex justify-center">
      <div class="w-full lg:w-10/12">
        <h2 class="my-3 py-3 shadow-sm text-2xl text-center">글보기</h2>
      </div>
    </div>
    <div class="flex justify-center">
      <div class="w-full lg:w-10/12 text-start">
        <div class="my-2">
          <h2 class="text-secondary text-2xl font-semibold px-5 mb-5">
            {{ article.articleNo }}. {{ article.subject }}
          </h2>
        </div>
        <div class="flex">
          <div class="w-full lg:w-8/12">
            <div class="flex items-center">
              <img
                class="w-12 h-12 mr-2 rounded-full bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                alt="User Avatar" />
              <p class="font-bold text-lg">{{ article.userId }}</p>
            </div>
          </div>
          <div class="w-full lg:w-4/12 flex items-center justify-end">
            {{ article.registerTime }} | 조회 : {{ article.hit }}
          </div>
        </div>
        <div class="divider my-3"></div>
        <div class="text-secondary text-lg mx-5 min-h-96">
          {{ article.content }}
        </div>
        <div class="divider my-3"></div>
        <div class="flex mt-10">
          <div class="justify-start ml-5">
            <button
              type="button"
              class="bg-white text-gray-600 font-semibold py-2 px-4 border border-gray-600 rounded-md shadow-md hover:bg-gray-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-gray-600 focus:ring-opacity-50"
              @click="moveList">
              글목록
            </button>
          </div>
          <div class="justify-end mr-5 ml-auto">
            <button
              type="button"
              class="mr-3 bg-white text-emerald-600 font-semibold py-2 px-4 border border-emerald-600 rounded-md shadow-md hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-opacity-50"
              @click="moveModify">
              글수정
            </button>
            <button
              type="button"
              class="bg-white text-red-600 font-semibold py-2 px-4 border border-red-600 rounded-md shadow-md hover:bg-red-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-red-600 focus:ring-opacity-50"
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
