<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, getModifyArticle, modifyArticle } from "@/api/board";
import { useMemberStore } from "@/stores/member";
import Swal from "sweetalert2";
const memberStore = useMemberStore();
const { userId } = memberStore;
const router = useRouter();
const route = useRoute();

const props = defineProps({ type: String });

const isUseId = ref(false);

const article = ref({
  articleNo: 0,
  subject: "",
  content: "",
  userId: sessionStorage.getItem("userId"),
  userName: "",
  hit: 0,
  registerTime: "",
});

if (props.type === "modify") {
  let { articleno } = route.params;

  getModifyArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      isUseId.value = true;
    },
    (error) => {
      console.log(error);
    }
  );
  isUseId.value = true;
}

const subjectErrMsg = ref("");
const contentErrMsg = ref("");
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 입력해 주세요!";
    } else subjectErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => article.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 입력해 주세요!";
    } else contentErrMsg.value = "";
  },
  { immediate: true }
);

function onSubmit() {
  if (subjectErrMsg.value) {
    Swal.fire(subjectErrMsg.value);
  } else if (contentErrMsg.value) {
    Swal.fire(contentErrMsg.value);
  } else {
    props.type === "regist" ? writeArticle() : updateArticle();
  }
}

function writeArticle() {
  registArticle(
    article.value,
    (response) => {
      let msg = response.data;
      if (response.status == 201) {
        msg = "글등록이 완료되었습니다.";
        Swal.fire({
          icon: "success",
          title: msg,
          showConfirmButton: false,
          timer: 1500,
        });
        moveList();
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

function updateArticle() {
  modifyArticle(
    article.value,
    (response) => {
      let msg = "글수정에 실패했습니다.";
      if (response.status == 200) {
        msg = "글이 수정되었습니다.";
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
      moveList();
    },
    (error) => console.log(error)
  );
}

function moveList() {
  router.replace({ name: "article-list" });
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="min-h-screen md:px-20 pt-6">
      <div class="border bg-white rounded-md px-6 py-10 max-w-2xl mx-auto">
        <div class="flex">
          <div class="flex-1 space-y-4">
            <div>
              <label for="subject" class="text-lx">제목:</label>
              <input
                type="text"
                placeholder="제목..."
                id="subject"
                v-model="article.subject"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full" />
            </div>
            <div>
              <label for="content" class="block mb-2 text-lg">내용:</label>
              <textarea
                id="content"
                cols="30"
                rows="10"
                v-model="article.content"
                class="w-full p-4 text-gray-600 bg-emerald-50 outline-none rounded-md"></textarea>
            </div>
          </div>
        </div>
        <div class="mt-6 text-center">
          <button
            type="submit"
            class="px-6 py-2 mx-auto rounded-md text-lg font-semibold text-emerald-50 bg-emerald-600"
            v-if="type === 'regist'">
            글작성
          </button>
          <button
            type="submit"
            class="px-6 py-2 mx-auto rounded-md text-lg font-semibold text-emerald-50 bg-emerald-600"
            v-else>
            글수정
          </button>
          <button
            type="button"
            class="inline-flex px-6 py-2 ml-3 block border rounded-md text-lg font-semibold text-emerald-600 bg-white-100"
            @click="moveList">
            목록으로이동...
          </button>
        </div>
      </div>
    </div>
  </form>
</template>

<style scoped></style>
