import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/comment/list`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/comment/detail/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log("commentjs article", article);
  local
    .post(`/comment/regist`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function modifyArticle(article, success, fail) {
  local
    .put(`/comment/modify`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/comment/remove/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  modifyArticle,
  deleteArticle,
};
