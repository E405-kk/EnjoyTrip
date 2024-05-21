import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/trip/trip`).then(success).catch(fail);
}

function listTrip(param, success, fail) {
  local.get("/trip/tripSearch", { params: param }).then(success).catch(fail);
}

function addPlanList(param, success, fail) {
  local.post("/trip/planRegist", param).then(success).catch(fail);
}

// function getPlanList(param, success, fail) {
//   local
//     .get("/trip/userTripPlanList", { params: param })
//     .then(success)
//     .catch(fail);
// }

function deletePlan(planId, success, fail) {
  local.delete(`/trip/planDelete/${planId}`).then(success).catch(fail);
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

function listPlan(userId, success, fail) {
  local.get(`/trip/planList/${userId}`).then(success).catch(fail);
}
function planDetail(planId, success, fail) {
  local.get(`/trip/planDetail/${planId}`).then(success).catch(fail);
}
export {
  listSido,
  listTrip,
  addPlanList,
  deletePlan,
  listMonthly,
  listRank,
  detailRank,
  detailMonthly,
  listPlan,
  planDetail,
};
