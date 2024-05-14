<script setup>
import { onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();

const { userInfo } = storeToRefs(memberStore);
const { userGetInfo, goModify, userId, userDelete } = memberStore;

onMounted(() => {
  userGetInfo(userId);
});

const go = () => {
  goModify();
};
const remove = () => {
  userDelete(userId);
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..." />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">{{ userInfo.userId }}</li>
                  <li class="list-group-item">{{ userInfo.userName }}</li>
                  <li class="list-group-item">{{ userInfo.userEmail }}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div>
          <button
            type="button"
            class="btn btn-outline-secondary mt-2"
            @click="go">
            수정
          </button>
          <button
            type="button"
            class="btn btn-outline-secondary mt-2"
            @click="remove">
            삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
