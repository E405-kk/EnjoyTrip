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
const user = ref({
  userId: "",
  userPwd: "",
  userName: "",
  userEmail: "",
});

onMounted(() => {
  if (props.type === "modify") {
    user.value = userInfo.value;
  }
});

const register = async () => {
  await userJoin(user.value);
  Swal.fire({
    icon: "success",
    title: "회원가입이 완료되었습니다.",
    showConfirmButton: false,
    timer: 1500,
  });
};

const modify = async () => {
  if (userPwdCheck.value === user.value.userPwd) {
    await userModify(user.value);
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
</script>

<template>
  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <div class="space-y-6">
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
            @keyup.enter="onSubmit"
            v-model="user.userEmail" />
        </div>
      </div>

      <div class="col-auto text-center">
        <button
          type="button"
          class="flex w-full justify-center bg-green-500 rounded-md px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          @click="onSubmit"
          v-if="type === 'regist'">
          회원가입
        </button>
        <button
          type="button"
          class="flex w-full justify-center bg-green-500 rounded-md px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          @click="onSubmit"
          v-else>
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
