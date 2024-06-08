<script setup>
import { ref } from "vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import CommentFormItem from "@/components/board/item/CommentFormItem.vue";
import ReplyListItem from "@/components/board/item/ReplyListItem.vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { listReply, deleteReply } from "@/api/reply";
import { onMounted } from "vue";
import Swal from "sweetalert2";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const props = defineProps({ comment: Object });
const emit = defineEmits(["update-comment", "delete-comment", "regist-reply"]);

const userId = userInfo.value.userId;
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  articleno: 0,
});
const isEditing = ref(false);
const showRegist = ref(false);
const replies = ref([]);
const isClicked = ref(false);

onMounted(() => {
  param.value.articleno = props.comment.idx;
});

const onPageChange = (val) => {
  currentPage.value = val;
  param.value.pgno = val;
  showReplyList();
};
const updateComment = (commentIdx, newContent) => {
  emit("update-comment", commentIdx, newContent);
  isEditing.value = false;
};

const cancelEdit = () => {
  isEditing.value = false;
};

const deleteComment = () => {
  emit("delete-comment", props.comment.idx);
};
function getImageUrl() {
  if (props.comment.img) {
    var url = "/src/assets/users/";
    url += props.comment.img;
    return url;
  } else {
    return "/src/assets/user.png";
  }
}

const showReplyList = () => {
  listReply(
    param.value,
    ({ data }) => {
      replies.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
      isClicked.value = true;
      console.log(data);
    },
    (error) => {
      console.log(error);
    }
  );
};
const handleUpdateReply = (replyIdx, newReply) => {
  // 댓글 데이터 업데이트 로직 (CommentFormItem에서 처리)
  const reply = replies.value.find((reply) => reply.idx === replyIdx);
  if (reply) {
    reply.content = newReply.content;
  }
};

const handleDeleteReply = (replyIdx) => {
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
      deleteReply(
        replyIdx,
        (response) => {
          let msg = response.data;
          if (response.status == 200) {
            msg = "댓글이 삭제되었습니다.";
            // 출력
            replies.value = replies.value.filter(
              (reply) => reply.idx !== replyIdx
            );
            emit("regist-reply");
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
const hideReplyList = () => {
  isClicked.value = false;
};
const handleWriteReply = () => {
  emit("regist-reply");
  showReplyList();
};
</script>

<template>
  <div>
    <div v-if="isEditing">
      <CommentFormItem
        :curcomment="comment"
        type="modify"
        kind="comment"
        @update-comment="updateComment"
        @cancel-edit="cancelEdit" />
    </div>
    <div v-if="!isEditing" class="flex items-center mb-2">
      <img
        class="w-10 h-10 rounded-full mr-3"
        :src="getImageUrl()"
        alt="User Avatar" />
      <div class="flex-1">
        <div class="flex justify-between items-center">
          <span class="font-semibold text-gray-700">{{ comment.userId }}</span>
          <div class="flex">
            <button
              v-if="!showRegist"
              class="text-gray-700 ml-2"
              @click="showRegist = true">
              댓글 등록
            </button>
            <button
              v-if="showRegist"
              class="text-gray-700 ml-2"
              @click="showRegist = false">
              취소
            </button>
            <div v-if="comment.userId === userId">
              <button class="text-gray-700 ml-2" @click="isEditing = true">
                수정
              </button>
              <button class="text-gray-700 ml-2" @click="deleteComment">
                삭제
              </button>
            </div>
          </div>
        </div>
        <p class="text-sm text-gray-500">{{ comment.registerTime }}</p>
        <p class="text-gray-700">{{ comment.content }}</p>
      </div>
    </div>
    <div class="ml-10 my-4">
      <div v-if="comment.reply">
        <button
          class="text-emerald-600"
          v-if="!isClicked"
          @click="showReplyList">
          댓글 보기({{ comment.reply }})
        </button>
        <template v-else>
          <button class="text-gray-300" @click="hideReplyList">
            댓글 숨기기
          </button>
          <div class="border-b border-gray-200 pb-4 mb-4">
            <ReplyListItem
              v-for="reply in replies"
              :key="reply.idx"
              :reply="reply"
              @update-reply="handleUpdateReply"
              @delete-reply="handleDeleteReply"></ReplyListItem>
            <PageNavigation
              :current-page="currentPage"
              :total-page="totalPage"
              @pageChange="onPageChange"></PageNavigation>
          </div>
        </template>
      </div>
      <template v-if="showRegist">
        <CommentFormItem
          v-if="userId"
          type="regist"
          kind="reply"
          @write-reply="handleWriteReply"
          :parentidx="comment.idx"></CommentFormItem>
      </template>
    </div>
  </div>
</template>
<style scoped></style>
