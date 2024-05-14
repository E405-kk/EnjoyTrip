import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.get(`/user/login`, { params: param }).then(success).catch(fail);
}

async function userRegister(param, success, fail) {
  await local.post(`/user/register`, param).then(success).catch(fail);
}

async function userSearch(userId, success, fail) {
  await local.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function userUpdate(param, success, fail) {
  await local.put(`/user/modify`, param).then(success).catch(fail);
}

async function userRemove(userId, success, fail) {
  await local.post(`/user/remove/${userId}`).then(success).catch(fail);
}

async function findPwd(param, success, fail) {
  await local.post(`/user/findpwd`, param).then(success).catch(fail);
}

async function changePwd(param, success, fail) {
  await local.post(`/user/changepwd`, param).then(success).catch(fail);
}
export {
  userConfirm,
  userRegister,
  userSearch,
  userUpdate,
  userRemove,
  findPwd,
  changePwd,
};
