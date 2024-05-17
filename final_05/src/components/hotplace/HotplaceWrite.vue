<script setup>
import HotplaceFormItem from "./item/HotplaceFormItem.vue";
</script>

<template>
  <div class="fluid-container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm text-2xl text-center">
          핫플레이스 등록하기
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <HotplaceFormItem type="regist" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
