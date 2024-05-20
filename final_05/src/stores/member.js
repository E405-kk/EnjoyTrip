import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import Swal from "sweetalert2";
import {
  userConfirm,
  userRegister,
  userSearch,
  userUpdate,
  userRemove,
  findPwd,
  changePwd,
} from "@/api/user";
import { httpStatusCode } from "@/util/http-status";
import { useMenuStore } from "@/stores/menu";
export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();
  const userId = sessionStorage.getItem("userId");
  const menuStore = useMenuStore();
  const { changeMenuState } = menuStore;
  const userInfo = ref({
    userId: "",
    userPwd: "",
    userName: "",
    userEmail: "",
    joinDate: "",
    isAdmin: 0,
  });

  const isLogin = ref(false);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        let msg = response.data;
        if (response.status == httpStatusCode.CREATE) {
          msg = "로그인 되었습니다.";
          let { data } = response;
          let userId = data["userId"];
          isLogin.value = true;
          sessionStorage.setItem("userId", userId);
          userGetInfo(userId);
          Swal.fire({
            icon: "success",
            title: msg,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      },
      (error) => {
        isLogin.value = false;
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "아이디 및 비밀번호를 확인해주세요.",
        });
      }
    );
  };
  const userLogout = () => {
    Swal.fire({
      title: "로그아웃하시겠습니까?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#848484",
      confirmButtonText: "삭제",
      cancelButtonText: "취소",
    }).then((result) => {
      if (result.isConfirmed) {
        isLogin.value = false;
        userInfo.value = null;
        changeMenuState();
        sessionStorage.clear();
      }
    });
  };
  const userJoin = async (user) => {
    await userRegister(
      user,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          Swal.fire({
            icon: "success",
            title: "회원가입이 완료되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          goLogin();
        } else if (response.status === httpStatusCode.NOCONTENT) {
          Swal.fire({ icon: "error", title: "사용할 수 없는 아이디입니다." });
        }
      },
      (error) => {
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "회원가입에 실패했습니다.",
        });
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
  if (sessionStorage.getItem("userId")) {
    userGetInfo(userId);
  }
  const userModify = async (user) => {
    await userUpdate(
      user,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          Swal.fire({
            icon: "success",
            title: "회원정보가 수정되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          goMyPage();
        }
      },
      (error) => {
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "회원정보 수정에 실패했습니다.",
        });
      }
    );
  };
  const userDelete = async (userId) => {
    await userRemove(
      userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          Swal.fire({
            icon: "success",
            title: "회원 탈퇴되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          sessionStorage.clear();
          changeMenuState();
          router.push({ name: "main" });
        }
      },
      (error) => {
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "회원탈퇴에 실패했습니다.",
        });
      }
    );
  };
  const userFindPwd = async (user) => {
    await findPwd(
      user,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userInfo.value = user;
          Swal.fire({
            icon: "success",
            title: "회원정보가 확인되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          console.log(response.status);
          gochangePwd();
        }
      },
      (error) => {
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "아이디 및 이름을 확인해주세요.",
        });
      }
    );
  };
  const userChangePwd = async (user) => {
    await changePwd(
      user,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          Swal.fire({
            icon: "success",
            title: "비밀번호가 변경되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          goLogin();
        }
      },
      (error) => {
        console.error(error);
        Swal.fire({
          icon: "error",
          text: "비밀번호를 다시 확인해주세요.",
        });
      }
    );
  };
  const goLogin = () => {
    router.push({ name: "user-login" });
  };
  const goMyPage = () => {
    router.push({ name: "user-mypage" });
  };
  const goModify = () => {
    router.push({ name: "user-modify" });
  };
  const gochangePwd = () => {
    router.push({ name: "user-changePwd" });
  };
  return {
    isLogin,
    userInfo,
    userLogin,
    userLogout,
    userJoin,
    userGetInfo,
    userModify,
    userDelete,
    userFindPwd,
    userChangePwd,
    goModify,
    userId,
  };
});
