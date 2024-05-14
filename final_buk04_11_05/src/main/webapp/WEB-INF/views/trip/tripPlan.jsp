<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<!-- <link href="../assets/css/styles.css" rel="stylesheet" /> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<script src="./assets/js/etc/key.js"></script>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>
	<c:if test="${msg ne null }">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	<div class="row ms-5">
		<div class="col-md-9">
			<div class="alert alert-primary mt-3 text-center fw-bold"
				role="alert">계획 작성 페이지</div>
			<!-- 관광지 검색 start -->
			<form class="d-flex my-3" onsubmit="return false;" role="search">

				<select id="search-area" name="selected-sido"
					class="form-select me-2">
					<option value="0" selected>검색 할 지역 선택</option>
					<c:forEach var="sidoList" items="${ sidoList }">
						<option value="${sidoList.sidoCode}">${sidoList.sidoName}</option>
					</c:forEach>
				</select> <select id="search-content-id" class="form-select me-2">
					<option value="0" selected>관광지 유형</option>
					<option value="12">관광지</option>
					<option value="14">문화시설</option>
					<option value="15">축제공연행사</option>
					<option value="25">여행코스</option>
					<option value="28">레포츠</option>
					<option value="32">숙박</option>
					<option value="38">쇼핑</option>
					<option value="39">음식점</option>
				</select> <input id="search-keyword" name="keyword" class="form-control me-2"
					type="search" placeholder="검색어" aria-label="검색어" />
				<button id="btn-search" class="btn btn-outline-success"
					type="button">검색</button>
			</form>
			<!-- kakao map start -->
			<div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
			<!-- kakao map end -->
			<div class="row" style="width: 100%; height: 400px; overflow: auto">
				<table class="table table-striped" style="display: none">
					<thead>
						<tr>
							<th>대표이미지</th>
							<th>관광지명</th>
							<th>주소</th>
							<th>위도</th>
							<th>경도</th>
						</tr>
					</thead>
					<tbody id="trip-list"></tbody>
				</table>
			</div>
			<!-- 관광지 검색 end -->
		</div>
		<div class="col-md-3">
			<button id="tripPlanSaveBtn">저장하기</button>
			<div id="plan-list"></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="./assets/js/main.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5f70a755fe6b196dd3a3e0291d559aad&libraries=services,clusterer,drawing"></script>
	<script src="./assets/js/trip/tripPlan.js"></script>
</body>
</html>
