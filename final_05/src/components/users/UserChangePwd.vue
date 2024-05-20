<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const { userChangePwd } = memberStore;

const user = ref({
  userId: "",
  userPwd: "",
  userName: "",
  userEmail: "",
});
const userPwdCheck = ref("");

onMounted(() => {
  user.value = userInfo.value;
});

const changePwd = async () => {
  if (userPwdCheck.value === user.value.userPwd) {
    await userChangePwd(user.value);
  } else {
    alert("비밀번호를 다시 확인해주세요.");
    return;
  }
};
</script>

<template>
  <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <img
        class="mx-auto h-10 w-auto"
        src="@/assets/favicon-64x64.png"
        alt="Your Company" />
      <h2
        class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
        비밀번호 변경
      </h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <div>
          <label
            for="userpwd"
            class="block text-sm font-medium leading-6 text-gray-900"
            >비밀번호</label
          >
          <div class="mt-2">
            <input
              id="userpwd"
              name="userpwd"
              type="password"
              required
              v-model="user.userPwd"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6" />
          </div>
        </div>

        <div>
          <label
            for="userpwdcheck"
            class="block text-sm font-medium leading-6 text-gray-900"
            >비밀번호 확인</label
          >
          <div class="mt-2">
            <input
              id="userpwdcheck"
              name="userpwdcheck"
              type="password"
              required
              @keyup.enter="changePwd"
              v-model="userPwdCheck"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6" />
          </div>
        </div>

        <div>
          <button
            type="button"
            class="flex w-full justify-center rounded-md bg-green-500 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
            @click="changePwd">
            비밀번호 변경
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
