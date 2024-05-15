<script setup>
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();

const { userInfo } = storeToRefs(memberStore);
const { userId, goModify, userDelete } = memberStore;

const go = () => {
  goModify();
};
const remove = () => {
  if (confirm("정말로 회원 탈퇴하시겠습니까? ㅠㅠ")) {
    userDelete(userId);
  }
};
</script>

<template>
  <div class="flex flex-col justify-center items-center h-[80vh]">
    <div
      class="relative flex flex-col items-center rounded-[20px] w-[700px] max-w-[95%] mx-auto bg-white bg-clip-border shadow-3xl shadow-shadow-500 dark:!bg-navy-800 dark:text-white dark:!shadow-none p-3">
      <div class="mt-2 mb-8 w-full">
        <h4 class="px-2 text-2xl font-bold text-navy-700 dark:text-white">
          마이 페이지
        </h4>
      </div>

      <div class="mt-4 border-t border-gray-100 w-full">
        <dl class="divide-y divide-gray-100">
          <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
            <dt class="text-m font-medium leading-6 text-gray-900">ID</dt>
            <dd
              class="mt-1 text-m leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
              {{ userInfo.userId }}
            </dd>
          </div>
          <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
            <dt class="text-m font-medium leading-6 text-gray-900">이름</dt>
            <dd
              class="mt-1 text-m leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
              {{ userInfo.userName }}
            </dd>
          </div>
          <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
            <dt class="text-m font-medium leading-6 text-gray-900">이메일</dt>
            <dd
              class="mt-1 text-m leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
              {{ userInfo.userEmail }}
            </dd>
          </div>
          <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
            <dt class="text-m font-medium leading-6 text-gray-900">
              가입 날짜
            </dt>
            <dd
              class="mt-1 text-m leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
              {{ userInfo.joinDate }}
            </dd>
          </div>
        </dl>
      </div>
      <div class="flex mt-6">
        <button
          @click="go"
          class="rounded-xl bg-indigo-600 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-indigo-500 active:bg-indigo-600">
          정보 수정
        </button>
        <button
          @click="remove"
          class="ml-4 rounded-xl bg-red-500 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-red-400 active:bg-red-600">
          회원 탈퇴
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
