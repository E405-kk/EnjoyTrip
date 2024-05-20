<script setup>
import { ref, watch } from "vue";
import { registComment, modifyComment } from "@/api/comment.js";
import Swal from "sweetalert2";
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

function onSubmit() {
  props.type === "regist" ? writeComment() : updateComment();
}

function writeComment() {
  console.log(comment.value);
  registComment(
    comment.value,
    (response) => {
      let msg = response.data;

      if (response.status == 201) {
        msg = "댓글이 등록되었습니다.";
        emit("write-comment");
        // 등록 후에 textarea 비우기
        comment.value.content = "";
        Swal.fire({
          icon: "success",
          title: msg,
          showConfirmButton: false,
          timer: 1500,
        });
      } else {
        Swal.fire({
          icon: "error",
          text: msg,
        });
      }
    },
    (error) => console.log(error)
  );
}

function updateComment() {
  modifyComment(
    comment.value,
    (response) => {
      let msg = "댓글 수정에 실패했습니다.";
      if (response.status == 200) {
        msg = "댓글이 수정되었습니다.";
        emit("update-comment", comment.value.idx, comment.value);
        Swal.fire({
          icon: "success",
          title: msg,
          showConfirmButton: false,
          timer: 1500,
        });
      } else {
        Swal.fire({
          icon: "error",
          text: msg,
        });
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
    <div class="mt-6 border border-gray-300 rounded-md px-2 relative">
      <div class="flex items-center" style="height: 50px">
        <span class="font-semibold ml-1.5 mr-auto">{{ comment.userId }}</span>

        <div class="flex items-center justify-between mr-1 ml-auto">
          <button
            v-if="type === 'modify'"
            class="text-gray-600 py-1 px-2 hover:text-gray-800"
            @click="cancelModify">
            취소
          </button>
          <button
            type="submit"
            v-if="type === 'modify'"
            class="bg-emerald-100 text-emerald-700 font-semibold py-1 px-2 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50">
            수정
          </button>
          <button
            type="submit"
            v-if="type === 'regist'"
            class="bg-emerald-100 text-emerald-700 font-semibold py-1 px-2 border border-emerald-300 rounded-md shadow-sm hover:bg-emerald-200 hover:text-emerald-800 focus:outline-none focus:ring-2 focus:ring-emerald-300 focus:ring-opacity-50">
            등록
          </button>
        </div>
      </div>

      <textarea
        rows="3"
        v-model="comment.content"
        class="w-full border border-gray-300 rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-gray-300 focus:ring-opacity-50"
        placeholder="댓글을 입력하세요"></textarea>
    </div>
  </form>
</template>

<style scoped></style>
