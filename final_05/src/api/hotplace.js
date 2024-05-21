import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/hotplace/list`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/hotplace/detail/${articleno}`).then(success).catch(fail);
}

function registArticle(article, config, success, fail) {
  console.log("hotplacejs article", article);
  local.post(`/hotplace/regist`, article, config).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/hotplace/modify/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, config, success, fail) {
  local.put(`/hotplace/modify`, article, config).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/hotplace/remove/${articleno}`).then(success).catch(fail);
}

function checkGood(param, success, fail) {
  local.get(`/hotplace/good`, { params: param }).then(success).catch(fail);
}

function updateGood(param, success, fail) {
  console.log(param);

  local.put(`/hotplace/updateGood`, param).then(success).catch(fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
  checkGood,
  updateGood,
};
