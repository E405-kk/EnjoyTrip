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

onMounted(() => {
  user.value = userInfo.value;
});

const changePwd = async () => {
  await userChangePwd(user.value);
};
</script>

<template>
  <main class="row">
    <div class="col-lg-2 col-md-1"></div>
    <div class="login col-lg-8 col-md-10">
      <div class="login-title fw-bold fs-3 border-bottom pb-2 mt-2">
        비밀번호 변경
      </div>
      <div class="input-pwd mt-3 ms-3 me-3">
        <label for="userpwd" class="d-block">비밀번호: </label>
        <input
          type="password"
          class="form-control"
          id="userpwd"
          name="userpwd"
          placeholder="비밀번호..."
          v-model="user.userPwd" />
      </div>
      <div class="input-pwd-check mt-3 ms-3 me-3">
        <label for="userpwdcheck" class="d-block">비밀번호확인: </label>
        <input
          type="password"
          class="form-control"
          id="userpwdcheck"
          name="userpwdcheck"
          placeholder="비밀번호확인..." />
      </div>
      <div class="input-bottom mt-3 text-end border-top pt-3 me-3">
        <button
          type="button"
          class="btn btn-outline-success fs-6 p-2"
          id="btn-findpwd"
          @click="changePwd">
          비밀번호 변경
        </button>
      </div>
    </div>
    <div class="col-lg-2 col-md-1"></div>
  </main>
</template>

<style scoped></style>
