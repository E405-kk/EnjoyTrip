<script setup>
import { ref, onMounted, watch } from "vue";
import { listComment } from "@/api/comment.js";
const props = defineProps({ article: Object });

import PageNavigation from "@/components/common/PageNavigation.vue";
import CommentFormItem from "@/components/board/item/CommentFormItem.vue";
import CommentListItem from "@/components/board/item/CommentListItem.vue";

const userId = sessionStorage.getItem("userId");
const articleno = ref(0);
const comments = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  articleno: articleno.value,
});

watch(
  () => props.article.value,
  () => {
    console.log(props.article);
    param.articleno = props.article.articleNo;
    console.log(param.articleno);
  },
  { deep: true }
);

onMounted(() => {
  () => {
    param.articleno = props.article.articleNo;
  };
  getCommentList();
});

const getCommentList = () => {
  listComment(
    param.value,
    ({ data }) => {
      console.log(param.value);
      comments.value = data.articles;
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
  getCommentList();
};
</script>

<template>
  <div class="bg-white shadow-md rounded-lg p-5 mt-5">
    <h3 class="text-xl font-semibold text-gray-800 mb-4">댓글</h3>
    <div class="border-b border-gray-200 pb-4 mb-4">
      <CommentListItem
        v-for="comment in comments"
        :key="comment.idx"
        :comment="comment"></CommentListItem>
    </div>
    <div v-if="comments.length === 0" class="text-gray-500">
      댓글이 없습니다.
    </div>

    <PageNavigation
      :current-page="currentPage"
      :total-page="totalPage"
      @pageChange="onPageChange"></PageNavigation>

    <CommentFormItem
      v-if="userId"
      type="regist"
      :articleno="article.articleNo"></CommentFormItem>
  </div>
</template>

<style scoped></style>
