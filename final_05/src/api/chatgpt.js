import { localAxios } from "@/util/http-commons";

const local = localAxios();

function getAnswer(comment, success, fail) {
  local
    .post(`/api/chatgpt/prompt`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

export { getAnswer };
