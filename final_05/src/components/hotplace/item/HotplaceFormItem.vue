<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, getModifyArticle, modifyArticle } from "@/api/hotplace";
import Swal from "sweetalert2";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const router = useRouter();
const route = useRoute();
const props = defineProps({ type: String, latlng: Object, detailAddr: String });

const isUseId = ref(false);
const imagePreview = ref("");
const article = ref({
  articleNo: 0,
  subject: "",
  content: "",
  userId: userInfo.value.userId,
  userName: "",
  hit: 0,
  registerTime: "",
  latitude: 0,
  longitude: 0,
  addr: "",
  fileInfo: {
    saveFile: "",
    saveFolder: "",
    originalFile: "",
  },
  img: userInfo.value.img,
  visitDate: "",
});
watch(
  () => props.detailAddr,
  (value) => {
    article.value.addr = value;
  },
  { immediate: true }
);
watch(
  () => props.latlng.lat,
  () => {
    article.value.latitude = props.latlng.lat;
    article.value.longitude = props.latlng.lng;
  },
  { immediate: true }
);
if (props.type === "modify") {
  let { articleno } = route.params;

  getModifyArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      console.log(article.value);
      isUseId.value = true;
      getImageUrl();
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
  var formData = new FormData();
  var photoFile = document.getElementById("photo");
  const blob = new Blob([JSON.stringify(article.value)], {
    type: "application/json",
  });
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  };

  formData.append("file", photoFile.files[0]);
  formData.append("hotplaceDto", blob);
  registArticle(
    formData,
    config,
    (response) => {
      let msg = response.data;
      console.log(response);
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
  var formData = new FormData();
  var photoFile = document.getElementById("photo");
  const blob = new Blob([JSON.stringify(article.value)], {
    type: "application/json",
  });
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  };

  formData.append("file", photoFile.files[0]);
  formData.append("hotplaceDto", blob);
  modifyArticle(
    formData,
    config,
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
  router.replace({ name: "hotplace-list" });
}

const previewImage = (event) => {
  const file = event.target.files[0];
  if (file && file.type.startsWith("image/")) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      imagePreview.value = reader.result;
    };
  } else {
    // 파일이 이미지가 아닌 경우에 대한 처리
    console.error("이미지 파일을 선택해주세요.");
  }
};
function getImageUrl() {
  if (article.value == null) {
    return null;
  }
  if (article.value.fileInfo.saveFile) {
    var url = "/src/assets/upload/";
    url +=
      article.value.fileInfo.saveFolder + "/" + article.value.fileInfo.saveFile;
    imagePreview.value = url;
    return url;
  } else {
    imagePreview.value = "/src/assets/about-bg.jpg";
    return "/src/assets/about-bg.jpg";
  }
}
</script>

<template>
  <form>
    <div class="min-h-screen ml-10 py-3">
      <div
        class="border rounded-lg shadow-lg px-6 py-10 max-w-2xl mx-auto bg-grey-lighter">
        <div class="flex">
          <div class="flex-1 space-y-4">
            <label
              class="w-60 mx-auto flex flex-col items-center justify-center p-3 bg-white text-blue rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue hover:text-white">
              <div
                v-if="imagePreview"
                style="
                  width: 60%;
                  height: auto;
                  aspect-ratio: 4/3;
                  max-width: 100%;
                ">
                <img
                  :src="imagePreview"
                  class="fluid-container"
                  style="width: 100%; height: 100%; object-fit: cover" />
              </div>
              <template v-else>
                <svg
                  class="w-10 h-10"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20">
                  <path
                    d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z" />
                </svg>
                <span class="mt-2 text-base leading-normal"> 사진 업로드</span>
              </template>

              <input
                type="file"
                class="hidden"
                name="photo"
                id="photo"
                @change="previewImage"
                accept="image/*" />
            </label>
            <div>
              <label for="subject" class="text-lx">제목:</label>
              <input
                type="text"
                placeholder="제목을 입력하세요."
                id="subject"
                v-model="article.subject"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full" />
            </div>
            <div>
              <label for="addr" class="text-lx">주소:</label>
              <div
                id="addr"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full">
                {{ article.addr }}
              </div>
            </div>
            <div>
              <label for="visitDate" class="text-lx">방문 날짜:</label>
              <input
                type="date"
                id="visitDate"
                v-model="article.visitDate"
                class="mt-2 outline-none py-2 px-2 text-md border-2 rounded-md w-full" />
            </div>
            <div>
              <label for="content" class="block mb-2 text-lg">내용:</label>
              <textarea
                id="content"
                cols="10"
                rows="5"
                v-model="article.content"
                class="w-full p-4 text-gray-600 bg-emerald-50 outline-none rounded-md"></textarea>
            </div>
          </div>
        </div>
        <div class="mt-6 text-center">
          <button
            type="submit"
            class="px-6 py-2 mx-auto rounded-md text-lg font-semibold text-emerald-50 bg-emerald-600"
            @click.prevent="onSubmit"
            v-if="type === 'regist'">
            글작성
          </button>
          <button
            type="submit"
            class="px-6 py-2 mx-auto rounded-md text-lg font-semibold text-emerald-50 bg-emerald-600"
            @click.prevent="onSubmit"
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
