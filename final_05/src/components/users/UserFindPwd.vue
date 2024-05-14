<script setup>
import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
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
  <div class="col-lg-2 col-md-1"></div>
  <div class="login col-lg-8 col-md-10">
    <div class="login-title fw-bold fs-3 border-bottom pb-2 mt-2">
      비밀번호 찾기
    </div>
    <div class="input-id mt-3 ms-3 me-3">
      <label for="userid" class="form-label">아이디:</label>
      <input
        type="text"
        class="form-control"
        id="userid"
        name="userid"
        placeholder="아이디..."
        v-model="user.userId" />
    </div>
    <div class="input-name mt-3 ms-3 me-3">
      <label for="username">이름:</label>
      <input
        type="text"
        class="form-control"
        id="username"
        name="username"
        placeholder="이름..."
        v-model="user.userName" />
    </div>

    <div class="input-bottom mt-3 text-end border-top pt-3 me-3">
      <button
        type="button"
        class="btn btn-outline-success fs-6 p-2"
        id="btn-findpwd"
        @click="findPwd">
        비밀번호 찾기
      </button>
    </div>
  </div>
  <div class="col-lg-2 col-md-1"></div>
</template>

<style scoped></style>
