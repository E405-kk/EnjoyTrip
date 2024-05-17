<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/hotplace.js";

import VSelect from "@/components/common/VSelect.vue";
import HotplaceListItem from "@/components/hotplace/item/HotplaceListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "글번호", value: "article_no" },
  { text: "제목", value: "subject" },
  { text: "작성자아이디", value: "user_id" },
]);

const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getArticleList();
});

const changeKey = (val) => {
  param.value.key = val;
};

const getArticleList = () => {
  listArticle(
    param.value,
    ({ data }) => {
      articles.value = data.articles;
      console.log(articles.value);
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

const onPageChange = (val) => {
  currentPage.value = val;
  param.value.pgno = val;
  getArticleList();
};

const moveWrite = () => {
  if (sessionStorage.getItem("userId")) {
    router.push({ name: "hotplace-write" });
  } else {
    alert("로그인이 필요한 페이지입니다");
    router.push({ name: "user-login" });
  }
};
</script>

<template>
  <div class="fluid-container mb-20">
    <div class="row justify-content-center">
      <h2 class="my-3 py-3 shadow-sm text-2xl text-center">핫플레이스</h2>
      <div class="flex items-center justify-center">
        <div class="ml-40 mr-auto">
          <button
            type="button"
            @click="moveWrite"
            class="bg-white text-emerald-600 font-semibold py-2 px-4 border border-emerald-600 rounded-md shadow-md hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-opacity-50">
            글쓰기
          </button>
        </div>
        <div class="mr-40 ml-auto">
          <form class="flex">
            <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
            <div class="gap-4">
              <input
                type="text"
                class="h-9 min-w-[10rem] rounded-lg border-emerald-500 indent-4 text-emerald-900 shadow-lg focus:outline-none focus:ring focus:ring-emerald-500"
                v-model="param.word"
                placeholder="검색어..." />
              <button
                class="h-9 min-w-[5rem] rounded-lg border text-emerald-50 shadow-lg bg-emerald-500 hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring focus:ring-emerald-500"
                type="button"
                @click="getArticleList">
                검색
              </button>
            </div>
          </form>
        </div>
      </div>

      <!-- component -->
      <div class="relative pt-2 lg:pt-2 min-h-screen">
        <div
          class="bg-cover w-full flex justify-center items-center"
          style="background-image: url('/images/mybackground.jpeg')">
          <div
            class="w-full bg-white p-5 bg-opacity-40 backdrop-filter backdrop-blur-lg">
            <div
              class="w-12/12 mx-auto rounded-2xl bg-white p-5 bg-opacity-40 backdrop-filter backdrop-blur-lg">
              <div
                class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3 text-center px-2 mx-auto">
                <HotplaceListItem
                  v-for="article in articles"
                  :key="article.articleNo"
                  :article="article"></HotplaceListItem>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <PageNavigation
      :current-page="currentPage"
      :total-page="totalPage"
      @pageChange="onPageChange"></PageNavigation>
  </div>
</template>

<style scoped></style>
