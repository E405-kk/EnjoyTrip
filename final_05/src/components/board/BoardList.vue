<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board.js";

import VSelect from "@/components/common/VSelect.vue";
import BoardListItem from "@/components/board/item/BoardListItem.vue";
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
  console.log("BoarList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const getArticleList = () => {
  console.log("서버에서 글목록 얻어오자!!!", param.value);
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
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getArticleList();
};

const moveWrite = () => {
  router.push({ name: "article-write" });
};
</script>

<template>
  <div class="fluid-container mb-20">
    <div class="row justify-content-center">
      <h2 class="my-3 py-3 shadow-sm text-2xl text-center">Q&A</h2>
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

      <div class="fluid-container mx-40 my-10">
        <div class="overflow-x-auto">
          <table class="min-w-full bg-white shadow-md rounded-xl">
            <thead>
              <tr class="bg-emerald-100 text-gray-700">
                <th class="py-3 px-4 text-left">글번호</th>
                <th class="py-3 px-4 text-left">제목</th>
                <th class="py-3 px-4 text-left">조회수</th>
                <th class="py-3 px-4 text-left">작성일</th>
              </tr>
            </thead>
            <tbody class="text-blue-gray-900">
              <BoardListItem
                v-for="article in articles"
                :key="article.articleNo"
                :article="article"></BoardListItem>
            </tbody>
          </table>
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
