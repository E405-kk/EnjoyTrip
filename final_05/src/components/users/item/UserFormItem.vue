<script setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import Swal from "sweetalert2";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const { userJoin, userModify } = memberStore;

const props = defineProps({ type: String });
const userPwdCheck = ref("");
const imagePreview = ref("");
const user = ref({
  userId: "",
  userPwd: "",
  userName: "",
  userEmail: "",
  img: "",
});

onMounted(() => {
  if (props.type === "modify") {
    user.value = userInfo.value;
    getImageUrl();
  }
});

const register = async () => {
  var formData = new FormData();
  var photoFile = document.getElementById("photo");
  const blob = new Blob([JSON.stringify(user.value)], {
    type: "application/json",
  });
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  };

  formData.append("file", photoFile.files[0]);
  formData.append("userDto", blob);
  await userJoin(formData, config);

  Swal.fire({
    icon: "success",
    title: "회원가입이 완료되었습니다.",
    showConfirmButton: false,
    timer: 1500,
  });
};

const modify = async () => {
  if (userPwdCheck.value === user.value.userPwd) {
    var formData = new FormData();
    var photoFile = document.getElementById("photo");
    const blob = new Blob([JSON.stringify(user.value)], {
      type: "application/json",
    });
    const config = {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    };
    formData.append("file", photoFile.files[0]);
    formData.append("userDto", blob);
    await userModify(formData, config);
    Swal.fire({
      icon: "success",
      title: "회원정보가 수정되었습니다.",
      showConfirmButton: false,
      timer: 1500,
    });
  } else {
    Swal.fire("비밀번호를 다시 확인해주세요.");
    return;
  }
};
const nameErrMsg = ref("");
const idErrMsg = ref("");
const pwdErrMsg = ref("");
watch(
  () => user.value.userName,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      nameErrMsg.value = "이름을 입력해 주세요!";
    } else nameErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => user.value.userId,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      idErrMsg.value = "아이디를 입력해 주세요!";
    } else idErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => user.value.userPwd,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      pwdErrMsg.value = "비밀번호를 입력해 주세요!";
    } else pwdErrMsg.value = "";
  },
  { immediate: true }
);
function onSubmit() {
  if (nameErrMsg.value) {
    Swal.fire(nameErrMsg.value);
  } else if (idErrMsg.value) {
    Swal.fire(idErrMsg.value);
  } else if (pwdErrMsg.value) {
    Swal.fire(pwdErrMsg.value);
  } else if (userPwdCheck.value !== user.value.userPwd) {
    Swal.fire("비밀번호를 다시 확인해주세요!");
  } else {
    props.type === "regist" ? register() : modify();
  }
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
  if (user.value == null) {
    return null;
  }
  if (user.value.img != null) {
    var url = "/src/assets/users/";
    url += user.value.img;
    imagePreview.value = url;
    return url;
  } else {
    imagePreview.value = "/src/assets/user.png";
    return "/src/assets/user.png";
  }
}
</script>

<template>
  <form>
    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <label
          class="w-60 mx-auto flex flex-col items-center justify-center px-4 py-6 bg-white text-blue rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue hover:text-white">
          <div v-if="imagePreview">
            <img :src="imagePreview" class="fluid-container" />
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
          <label
            for="username"
            class="block text-sm font-medium leading-6 text-gray-900"
            >이름 :
          </label>
          <div class="mt-2">
            <input
              type="text"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              placeholder="이름..."
              required
              v-model="user.userName" />
          </div>
        </div>

        <div>
          <label
            for="userid"
            class="inline text-sm font-medium leading-6 text-gray-900"
            >아이디 :
          </label>

          <input
            type="text"
            class="mt-2 pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            placeholder="아이디..."
            v-model="user.userId"
            required
            v-if="type === 'regist'" />
          <span v-if="type === 'modify'">{{ user.userId }}</span>
        </div>

        <div>
          <label
            for="userpwd"
            class="block text-sm font-medium leading-6 text-gray-900"
            >비밀번호 :
          </label>
          <div class="mt-2">
            <input
              type="password"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              placeholder="비밀번호..."
              required
              v-model="user.userPwd" />
          </div>
        </div>

        <div>
          <label
            for="pwdcheck"
            class="block text-sm font-medium leading-6 text-gray-900"
            >비밀번호 확인:
          </label>
          <div class="mt-2">
            <input
              type="password"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              placeholder="비밀번호 확인..."
              required
              v-model="userPwdCheck" />
          </div>
        </div>

        <div>
          <label
            for="useremail"
            class="block text-sm font-medium leading-6 text-gray-900"
            >이메일:
          </label>
          <div class="mt-2">
            <input
              type="text"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              placeholder="이메일..."
              required
              @keyup.enter.prevent="onSubmit"
              v-model="user.userEmail" />
          </div>
        </div>

        <div class="col-auto text-center">
          <button
            type="submit"
            class="flex w-full justify-center bg-green-500 rounded-md px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            @click.prevent="onSubmit"
            v-if="type === 'regist'">
            회원가입
          </button>
          <button
            type="submit"
            class="flex w-full justify-center bg-green-500 rounded-md px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            @click.prevent="onSubmit"
            v-else>
            수정
          </button>
        </div>
      </div>
    </div>
  </form>
</template>

<style scoped></style>
