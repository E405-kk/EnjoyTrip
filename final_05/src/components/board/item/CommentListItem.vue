<script setup>
import { ref } from "vue";
import CommentFormItem from "@/components/board/item/CommentFormItem.vue";

const props = defineProps({ comment: Object });
const emit = defineEmits(["update-comment", "delete-comment"]);

const userId = sessionStorage.getItem("userId");

const isEditing = ref(false);

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
</script>

<template>
  <div>
    <div v-if="isEditing">
      <CommentFormItem
        :curcomment="comment"
        type="modify"
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
          <div v-if="comment.userId === userId">
            <button class="text-gray-700 ml-2" @click="isEditing = true">
              수정
            </button>
            <button class="text-gray-700 ml-2" @click="deleteComment">
              삭제
            </button>
          </div>
        </div>
        <p class="text-sm text-gray-500">{{ comment.registerTime }}</p>
        <p class="text-gray-700">{{ comment.content }}</p>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
