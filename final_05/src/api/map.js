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

function listMonthly(success, fail) {
  local.get("/trip/monthlyList").then(success).catch(fail);
}

function listRank(success, fail) {
  local.get("/trip/rankList").then(success).catch(fail);
}

function detailMonthly(idx, success, fail) {
  local.get(`/trip/monthlyDetail/${idx}`).then(success).catch(fail);
}

function detailRank(idx, success, fail) {
  local.get(`/trip/tripDetail/${idx}`).then(success).catch(fail);
}
export {
  listSido,
  listTrip,
  addPlanList,
  getPlanList,
  deletePlanList,
  listMonthly,
  listRank,
  detailRank,
  detailMonthly,
};
