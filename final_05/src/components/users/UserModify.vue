<script setup>
import UserFormItem from "@/components/users/item/UserFormItem.vue";
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원 정보 수정</mark>
        </h2>
      </div>
      <UserFormItem type="modify" />
    </div>
  </div>
</template>

<style scoped></style>
