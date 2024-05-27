import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.get(`/user/login`, { params: param }).then(success).catch(fail);
}

async function userRegister(param, config, success, fail) {
  await local.post(`/user/register`, param, config).then(success).catch(fail);
}

async function userSearch(userId, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function userUpdate(param, config, success, fail) {
  await local.put(`/user/modify`, param, config).then(success).catch(fail);
}

async function userRemove(userId, success, fail) {
  await local.delete(`/user/remove/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function findPwd(param, success, fail) {
  await local
    .get(`/user/findpwd`, {
      params: { userId: param.userId, userName: param.userName },
    })
    .then(success)
    .catch(fail);
}

async function changePwd(param, success, fail) {
  console.log(param);
  await local.put(`/user/changepwd`, param).then(success).catch(fail);
}
export {
  userConfirm,
  userRegister,
  userSearch,
  userUpdate,
  userRemove,
  findPwd,
  changePwd,
  tokenRegeneration,
  logout,
};
