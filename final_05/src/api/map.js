import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/trip/trip`).then(success).catch(fail);
}

function listTrip(param, success, fail) {
  local.get("/trip/tripSearch", { params: param }).then(success).catch(fail);
}

function addPlanList(param, success, fail) {
  local.post("/trip/tripPlanSave", param).then(success).catch(fail);
}

function getPlanList(param, success, fail) {
  local
    .get("/trip/userTripPlanList", { params: param })
    .then(success)
    .catch(fail);
}

function deletePlanList(param, success, fail) {
  local
    .delete(`/trip/tripPlanDelete/${param.userId}`)
    .then(success)
    .catch(fail);
}

export { listSido, listTrip, addPlanList, getPlanList, deletePlanList };
