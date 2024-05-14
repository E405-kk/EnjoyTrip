import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";

import {
  userConfirm,
  userRegister,
  userSearch,
  userUpdate,
  // userRemove,
  // findPwd,
  // changePwd,
} from "@/api/user";
import { httpStatusCode } from "@/util/http-status";

export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();

  const userInfo = ref({
    userId: "",
    userPwd: "",
    userName: "",
    userEmail: "",
  });

  const isLogin = ref(false);
  const isLoginError = ref(false);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("로그인 성공!!!!");
          let { data } = response;
          let userId = data["userId"];
          isLogin.value = true;
          isLoginError.value = false;
          sessionStorage.setItem("userId", userId);
        }
      },
      (error) => {
        console.log("로그인 실패!!!!");
        isLogin.value = false;
        isLoginError.value = true;
        console.error(error);
      }
    );
  };
  const userJoin = async (user) => {
    await userRegister(
      user,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("회원가입 성공!!!!");
          goLogin();
        }
      },
      (error) => {
        console.log("회원가입 실패!!!!");
        console.error(error);
      }
    );
  };

  const userGetInfo = async (userId) => {
    await userSearch(
      userId,
      ({ data }) => {
        userInfo.value = data.user;
      },
      (error) => {
        console.error(error);
      }
    );
  };
  const userModify = async (user) => {
    await userUpdate(
      user,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("회원 정보 수정 성공!!!!");
          goMyPage();
        }
      },
      (error) => {
        console.log("회원 정보 수정 실패!!!!");
        console.error(error);
      }
    );
  };
  const goLogin = () => {
    router.push({ name: "user-login" });
  };
  const goMyPage = () => {
    router.push({ name: "user-mypage" });
  };
  return {
    isLogin,
    isLoginError,
    userInfo,
    userLogin,
    userJoin,
    userGetInfo,
    userModify,
  };
});
