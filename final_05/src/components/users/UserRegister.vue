<script setup>
import UserFormItem from "@/components/users/item/UserFormItem.vue";
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
        회원가입
      </h2>
    </div>
    <UserFormItem type="regist" />
  </div>
</template>

<style scoped></style>
