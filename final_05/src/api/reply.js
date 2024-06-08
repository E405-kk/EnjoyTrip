import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listReply(param, success, fail) {
  local.get(`/reply/list`, { params: param }).then(success).catch(fail);
}

function registReply(comment, success, fail) {
  console.log("replyjs article", comment);
  local
    .post(`/reply/regist`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function modifyReply(comment, success, fail) {
  local.put(`/reply/modify`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteReply(idx, success, fail) {
  local.delete(`/reply/remove/${idx}`).then(success).catch(fail);
}

export { listReply, registReply, modifyReply, deleteReply };
