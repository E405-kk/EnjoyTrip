<script setup>
import { ref, watch } from "vue";
import { registComment, modifyComment } from "@/api/comment.js";
const props = defineProps({
  type: String,
  articleno: Number,
  curcomment: {
    type: Object,
    default: () => ({}), // 빈 객체를 기본값으로 사용
  },
});
const emit = defineEmits(["write-comment", "update-comment", "cancel-edit"]);
const comment = ref({
  idx: props.curcomment.idx,
  articleNo: props.articleno,
  userId: sessionStorage.getItem("userId"),
  content: props.curcomment.content,
  registerTime: props.curcomment.registerTime,
});

// const contentErrMsg = ref("");
// watch(
//   () => comment.value.content,
//   (value) => {
//     let len = value.length;
//     if (len == 0 || len > 500) {
//       contentErrMsg.value = "내용을 확인해 주세요!!!";
//     } else contentErrMsg.value = "";
//   },
//   { immediate: true }
// );

function onSubmit() {
  // if (contentErrMsg.value) {
  //   alert(contentErrMsg.value);
  // } else {
  props.type === "regist" ? writeComment() : updateComment();
  // }
}

function writeComment() {
  console.log(comment.value);
  registComment(
    comment.value,
    (response) => {
      let msg = response.data;

      if (response.status == 201) {
        msg = "글등록이 완료되었습니다.";
        emit("write-comment");
        // 등록 후에 textarea 비우기
        comment.value.content = "";
      }
      alert(msg);
    },
    (error) => console.log(error)
  );
}

function updateComment() {
  modifyComment(
    comment.value,
    (response) => {
      let msg = "댓글 수정 처리시 문제 발생했습니다.";
      if (response.status == 200) {
        msg = "댓글 수정이 완료되었습니다.";
        console.log(comment.value);
        emit("update-comment", comment.value.idx, comment.value);
        alert(msg);
      }
    },
    (error) => console.log(error)
  );
}

function cancelModify() {
  emit("cancel-edit");
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mt-6 border border-gray-300 rounded-md p-2 relative">
      <div class="mb-2">
        <span class="font-semibold">{{ comment.userId }}</span>
      </div>
      <textarea
        rows="3"
        v-model="comment.content"
        class="w-full border border-gray-300 rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
        placeholder="댓글을 입력하세요"></textarea>
      <div
        class="flex flex-col justify-between absolute top-0 right-0 mt-2 mr-2 space-y-2">
        <button
          v-if="type === 'modify'"
          class="text-gray-600 hover:text-gray-800"
          @click="cancelModify">
          취소
        </button>
        <button
          type="submit"
          v-if="type === 'modify'"
          class="bg-emerald-100 text-emerald-700 font-semibold py-2 px-4 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50">
          수정
        </button>
        <button
          type="submit"
          v-if="type === 'regist'"
          class="bg-emerald-100 text-emerald-700 font-semibold py-2 px-4 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50">
          등록
        </button>
      </div>
    </div>
  </form>
</template>

<style scoped></style>
