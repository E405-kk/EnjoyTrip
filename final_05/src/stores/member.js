import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";
import Swal from "sweetalert2";
import {
  userConfirm,
  userRegister,
  userSearch,
  userUpdate,
  userRemove,
  findPwd,
  tokenRegeneration,
  logout,
} from "@/api/user";
import { httpStatusCode } from "@/util/http-status";
import { useMenuStore } from "@/stores/menu";
export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();
  const menuStore = useMenuStore();
  const { changeMenuState } = menuStore;
  const userInfo = ref({});
  const isValidToken = ref(false);
  const isLogin = ref(false);
  const isLoginError = ref(false);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        let msg = response.data;
        if (response.status == httpStatusCode.OK) {
          msg = "로그인 되었습니다.";
          let { data } = response;
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];
          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;
          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);

          Swal.fire({
            icon: "success",
            title: msg,
            showConfirmButton: false,
            timer: 1200,
          });
        }
      },
      (error) => {
        isLogin.value = false;
        isValidToken.value = false;
        isLoginError.value = true;
        console.error(error);

        Swal.fire({
          icon: "error",
          text: "아이디 및 비밀번호를 확인해주세요.",
        });
      }
    );
  };
  const userLogout = async () => {
    await Swal.fire({
      title: "로그아웃하시겠습니까?",
      icon: "question",
      showCancelButton: true,
      confirmButtonColor: "#4169E1",
      cancelButtonColor: "#848484",
      confirmButtonText: "확인",
      cancelButtonText: "취소",
    }).then((result) => {
      if (result.isConfirmed) {
        logout(
          userInfo.value.userId,
          (response) => {
            if (response.status === httpStatusCode.OK) {
              isLogin.value = false;
              userInfo.value = null;
              isValidToken.value = false;
              changeMenuState();
              sessionStorage.removeItem("accessToken");
              sessionStorage.removeItem("refreshToken");
              Swal.fire({
                icon: "success",
                title: "로그아웃 되었습니다.",
                showConfirmButton: false,
                timer: 1200,
              }).then(() => {
                // 페이지 이동
                router.push("/");
              });
            } else {
              console.error("유저 정보 없음!!!!");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    });
  };
  const userJoin = async (user, config) => {
    await userRegister(
      user,
      config,
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

  const userGetInfo = async (token) => {
    let decodeToken = jwtDecode(token);
    console.log(decodeToken);
    await userSearch(
      decodeToken.userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userInfo.value = response.data.user;
          console.log(userInfo.value);
        } else {
          console.log("유저 정보 없음!!!!");
        }
      },
      async (error) => {
        console.error(
          "g[토큰 만료되어 사용 불가능.] : ",
          error.response.status,
          error.response.statusText
        );
        isValidToken.value = false;

        await tokenRegenerate();
      }
    );
  };

  const tokenRegenerate = async () => {
    await tokenRegeneration(
      JSON.stringify(userInfo.value),
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let accessToken = response.data["access-token"];
          sessionStorage.setItem("accessToken", accessToken);
          isValidToken.value = true;
        }
      },
      async (error) => {
        // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
        if (error.response.status === httpStatusCode.UNAUTHORIZED) {
          // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
          await logout(
            userInfo.value.userId,
            (response) => {
              if (response.status === httpStatusCode.OK) {
                console.log("리프레시 토큰 제거 성공");
              } else {
                console.log("리프레시 토큰 제거 실패");
              }
              alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
              isLogin.value = false;
              userInfo.value = null;
              isValidToken.value = false;
              router.push({ name: "user-login" });
            },
            (error) => {
              console.error(error);
              isLogin.value = false;
              userInfo.value = null;
            }
          );
        }
      }
    );
  };

  const userModify = async (user, config) => {
    await userUpdate(
      user,
      config,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          Swal.fire({
            icon: "success",
            title: "회원정보가 수정되었습니다.",
            showConfirmButton: false,
            timer: 1500,
          });
          console.log(userInfo.value);
          userGetInfo(userInfo.value.refreshToken);
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
            title: "이메일로 임시 비밀번호가 전송되었습니다.",
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
          text: "아이디 및 이메일을 확인해주세요.",
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
    goModify,
    tokenRegenerate,
    isLoginError,
    isValidToken,
  };
});
