<script setup>
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
import { onMounted } from "vue";
const { userInfo } = storeToRefs(memberStore);
const { goModify, userDelete } = memberStore;
import { useRouter } from "vue-router";
const router = useRouter();
const userId = userInfo.value.userId;
import Swal from "sweetalert2";
onMounted(() => {
  if (!userId) {
    Swal.fire("로그인이 필요한 화면입니다.");
    router.push({ name: "user-login" });
  }
});

const go = () => {
  goModify();
};
const remove = () => {
  Swal.fire({
    title: "정말로 회원 탈퇴하시겠습니까? ㅠㅠ",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#848484",
    confirmButtonText: "탈퇴",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      userDelete(userId);
    }
  });
};
function getImageUrl() {
  if (userInfo.value == null) {
    return "/src/assets/user.png";
  }
  if (userInfo.value.img != null) {
    var url = "/src/assets/users/";
    url += userInfo.value.img;
    return url;
  } else {
    return "/src/assets/user.png";
  }
}
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
      <div class="flex">
        <img
          :src="getImageUrl()"
          style="width: 40%"
          class="mr-20 rounded-[20px]" />
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
      </div>

      <div class="flex mt-[50px]">
        <button
          @click="go"
          class="rounded-xl bg-green-500 px-4 py-2 text-base mb-3 font-medium text-white transition duration-200 hover:bg-green-600 active:bg-indigo-600">
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
