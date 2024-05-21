<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/notice.js";

import VSelect from "@/components/common/VSelect.vue";
import NoticeListItem from "@/components/notice/item/NoticeListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import Swal from "sweetalert2";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const router = useRouter();

const selectOption = ref([
  { text: "검색 조건", value: "" },
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
    router.push({ name: "notice-write" });
  } else {
    Swal.fire("로그인이 필요한 페이지입니다!");
    router.push({ name: "user-login" });
  }
};
</script>

<template>
  <div class="fluid-container mb-20">
    <div class="row justify-content-center">
      <div class="flex items-center justify-center">
        <div class="ml-40 mr-auto">
          <button
            v-if="userInfo.isAdmin == 1"
            type="button"
            @click="moveWrite"
            class="bg-white text-emerald-600 font-semibold py-2 px-4 border border-emerald-600 rounded-md shadow-md hover:bg-emerald-600 hover:text-white focus:outline-none focus:ring-2 focus:ring-emerald-600 focus:ring-opacity-50">
            글쓰기
          </button>
        </div>
        <div class="mr-40 ml-auto">
          <form class="flex" @submit.prevent="getArticleList">
            <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
            <div
              class="flex items-center max-w-md mx-auto bg-white rounded-lg"
              x-data="{ search: '' }">
              <div class="w-full">
                <input
                  type="search"
                  class="w-full px-4 py-1 text-gray-800 rounded-full focus:outline-none"
                  placeholder="search"
                  @keyup.enter="getArticleList"
                  x-model="search"
                  v-model="param.word" />
              </div>
              <div>
                <button
                  type="button"
                  class="flex items-center bg-emerald-500 justify-center w-12 h-12 text-white rounded-r-lg"
                  :class="
                    param.word.length > 0
                      ? 'bg-emerald-500'
                      : 'bg-gray-500 cursor-not-allowed'
                  "
                  :disabled="param.word.length == 0"
                  @click="getArticleList">
                  <svg
                    class="w-5 h-5"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg">
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                  </svg>
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>

      <div v-if="articles.length === 0" class="text-center text-xl my-10">
        작성된 글이 없습니다.
      </div>
      <div v-else>
        <div class="fluid-container mx-40 my-10">
          <div class="overflow-x-auto">
            <table class="min-w-full bg-white shadow-md rounded-xl">
              <thead>
                <tr class="bg-emerald-100 text-gray-700">
                  <th class="py-3 px-4 text-left">글번호</th>
                  <th class="py-3 px-4 text-left">제목</th>
                  <th class="py-3 px-4 text-left">작성자</th>
                  <th class="py-3 px-4 text-left">조회수</th>
                  <th class="py-3 px-4 text-left">작성일</th>
                </tr>
              </thead>
              <tbody class="text-blue-gray-900">
                <NoticeListItem
                  v-for="article in articles"
                  :key="article.articleNo"
                  :article="article"></NoticeListItem>
              </tbody>
            </table>
          </div>
        </div>

        <PageNavigation
          :current-page="currentPage"
          :total-page="totalPage"
          @pageChange="onPageChange"></PageNavigation>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
