import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/notice/list`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/notice/detail/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log("noticejs article", article);
  local
    .post(`/notice/regist`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/notice/modify/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local
    .put(`/notice/modify`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/notice/remove/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
};
