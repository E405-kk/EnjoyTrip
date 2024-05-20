<script setup>
import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
import Swal from "sweetalert2";
const memberStore = useMemberStore();
const { userFindPwd } = memberStore;

const user = ref({
  userId: "",
  userPwd: "",
  userName: "",
  userEmail: "",
});
const findPwd = async () => {
  await userFindPwd(user.value);
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
        비밀번호 찾기
      </h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <div>
          <label
            for="email"
            class="block text-sm font-medium leading-6 text-gray-900"
            >아이디</label
          >
          <div class="mt-2">
            <input
              id="email"
              name="email"
              type="email"
              autocomplete="email"
              required
              v-model="user.userId"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
          </div>
        </div>

        <div>
          <label
            for="email"
            class="block text-sm font-medium leading-6 text-gray-900"
            >이름</label
          >
          <div class="mt-2">
            <input
              id="email"
              name="email"
              type="email"
              autocomplete="email"
              required
              v-model="user.userName"
              class="pl-3 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6" />
          </div>
        </div>

        <div>
          <button
            type="button"
            class="flex w-full justify-center rounded-md bg-green-500 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
            @click="findPwd">
            비밀번호 찾기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
