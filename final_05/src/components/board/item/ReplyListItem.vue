<script setup>
import { ref } from "vue";
import CommentFormItem from "@/components/board/item/CommentFormItem.vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const props = defineProps({ reply: Object });
const emit = defineEmits(["update-reply", "delete-reply"]);

const userId = userInfo.value.userId;

const isEditing = ref(false);

const updateReply = (replyIdx, newContent) => {
  emit("update-reply", replyIdx, newContent);
  isEditing.value = false;
};

const cancelEdit = () => {
  isEditing.value = false;
};

const deleteReply = () => {
  emit("delete-reply", props.reply.idx);
};
function getImageUrl() {
  if (props.reply.img) {
    var url = "/src/assets/users/";
    url += props.reply.img;
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
        :curreply="reply"
        type="modify"
        kind="reply"
        @update-reply="updateReply"
        @cancel-edit="cancelEdit" />
    </div>
    <div v-if="!isEditing" class="flex items-center mb-2">
      <img
        class="w-10 h-10 rounded-full mr-3"
        :src="getImageUrl()"
        alt="User Avatar" />
      <div class="flex-1">
        <div class="flex justify-between items-center">
          <span class="font-semibold text-gray-700">{{ reply.userId }}</span>
          <div v-if="reply.userId === userId">
            <button class="text-gray-700 ml-2" @click="isEditing = true">
              수정
            </button>
            <button class="text-gray-700 ml-2" @click="deleteReply">
              삭제
            </button>
          </div>
        </div>
        <p class="text-sm text-gray-500">{{ reply.registerTime }}</p>
        <p class="text-gray-700">{{ reply.content }}</p>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
