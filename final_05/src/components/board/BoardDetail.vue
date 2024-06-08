<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";
import { listComment, deleteComment } from "@/api/comment";
import VArticle from "@/components/common/VArticle.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import CommentFormItem from "@/components/board/item/CommentFormItem.vue";
import CommentListItem from "@/components/board/item/CommentListItem.vue";
import Swal from "sweetalert2";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const userId = userInfo.value.userId;

const route = useRoute();
const router = useRouter();

const comments = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const { articleno } = route.params;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  articleno: 0,
});
const article = ref({});

onMounted(() => {
  getArticle();
});

const getArticle = () => {
  detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      param.value.articleno = articleno;
      getCommentList();
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

const getCommentList = () => {
  listComment(
    param.value,
    ({ data }) => {
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

const handleUpdateComment = (commentIdx, newComment) => {
  // 댓글 데이터 업데이트 로직 (CommentFormItem에서 처리)
  const comment = comments.value.find((comment) => comment.idx === commentIdx);
  if (comment) {
    comment.content = newComment.content;
  }
};

const handleDeleteComment = (commentIdx) => {
  Swal.fire({
    title: "댓글을 삭제하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#848484",
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      deleteComment(
        commentIdx,
        (response) => {
          let msg = response.data;
          if (response.status == 200) {
            msg = "댓글이 삭제되었습니다.";
            // 출력
            comments.value = comments.value.filter(
              (comment) => comment.idx !== commentIdx
            );
            Swal.fire({
              title: "삭제",
              text: msg,
              icon: "success",
            });
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
};
const handleWriteComment = () => {
  getCommentList();
};
</script>

<template>
  <div class="container mx-auto p-5">
    <div class="flex justify-center">
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

        <div class="bg-white shadow-md rounded-lg p-5 mt-5">
          <h3 class="text-xl font-semibold text-gray-800 mb-4">
            댓글 ({{ comments.length }})
          </h3>
          <div class="border-b border-gray-200 pb-4 mb-4">
            <CommentListItem
              v-for="comment in comments"
              :key="comment.idx"
              :comment="comment"
              @update-comment="handleUpdateComment"
              @regist-reply="handleWriteComment"
              @delete-comment="handleDeleteComment"></CommentListItem>
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
            kind="comment"
            @write-comment="handleWriteComment"
            :articleno="articleno"></CommentFormItem>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
