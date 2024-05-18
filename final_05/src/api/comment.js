import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listComment(param, success, fail) {
  local.get(`/comment/list`, { params: param }).then(success).catch(fail);
}

function registComment(comment, success, fail) {
  console.log("commentjs article", comment);
  local
    .post(`/comment/regist`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function modifyComment(comment, success, fail) {
  local
    .put(`/comment/modify`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function deleteComment(idx, success, fail) {
  local.delete(`/comment/remove/${idx}`).then(success).catch(fail);
}

export { listComment, registComment, modifyComment, deleteComment };
