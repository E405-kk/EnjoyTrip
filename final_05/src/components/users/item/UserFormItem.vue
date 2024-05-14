<script setup>
import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userJoin, userModify, userInfo } = memberStore;

const props = defineProps({ type: String });

const user = ref({
  userId: "",
  userPwd: "",
  userName: "",
  userEmail: "",
});

if (props.type === "modify") {
  user.value = userInfo.value;
}

const register = async () => {
  await userJoin(user.value);
};

const modify = async () => {
  await userModify(user.value);
};
</script>

<template>
  <div class="mb-3">
    <label for="username" class="form-label">이름 : </label>
    <input
      type="text"
      class="form-control"
      placeholder="이름..."
      v-model="user.userName" />
  </div>
  <div class="mb-3">
    <label for="userid" class="form-label">아이디 : </label>
    <input
      type="text"
      class="form-control"
      placeholder="아이디..."
      v-model="user.userId"
      v-if="type === 'regist'" />
    <div v-if="type === 'modify'">{{ user.userId }}</div>
  </div>
  <div class="mb-3">
    <label for="userpwd" class="form-label">비밀번호 : </label>
    <input
      type="text"
      class="form-control"
      placeholder="비밀번호..."
      v-model="user.userPwd" />
  </div>
  <div class="mb-3">
    <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
    <input
      type="text"
      class="form-control"
      id="pwdcheck"
      placeholder="비밀번호확인..." />
  </div>
  <div class="mb-3">
    <label for="emailid" class="form-label">이메일 : </label>
    <div class="input-group">
      <input
        type="text"
        class="form-control"
        placeholder="이메일아이디"
        v-model="user.userEmail" />
    </div>
  </div>
  <div class="col-auto text-center">
    <button
      type="button"
      class="btn btn-outline-primary mb-3"
      @click="register"
      v-if="type === 'regist'">
      회원가입
    </button>
    <button
      type="button"
      class="btn btn-outline-primary mb-3"
      @click="modify"
      v-else>
      수정
    </button>
    <button type="button" class="btn btn-outline-success ms-1 mb-3">
      초기화
    </button>
  </div>
</template>

<style scoped></style>
