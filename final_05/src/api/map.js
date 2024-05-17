import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/trip/trip`).then(success).catch(fail);
}

function listTrip(param, success, fail) {
  console.log(param);
  local.get("/trip/tripSearch", { params: param }).then(success).catch(fail);
}

function addPlanList(param, success, fail) {
  console.log(param);
  local.post("/trip/tripPlanSave", param).then(success).catch(fail);
}
export { listSido, listTrip, addPlanList };
