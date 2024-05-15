<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
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
};

const modify = async () => {
  if (userPwdCheck.value === user.value.userPwd) {
    await userModify(user.value);
  } else {
    alert("비밀번호를 다시 확인해주세요.");
    return;
  }
};
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
            v-model="user.userEmail" />
        </div>
      </div>

      <div class="col-auto text-center">
        <button
          type="button"
          class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          @click="register"
          v-if="type === 'regist'">
          회원가입
        </button>
        <button
          type="button"
          class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          @click="modify"
          v-else>
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
