<script setup>
import NoticeFormItem from "./item/NoticeFormItem.vue";
</script>

<template>
  <div class="fluid-container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm text-2xl text-center">공지사항 작성</h2>
      </div>
      <div class="col-lg-10 text-start">
        <NoticeFormItem type="regist" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
