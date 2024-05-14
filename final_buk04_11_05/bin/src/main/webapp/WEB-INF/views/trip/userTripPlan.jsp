<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.ssafy.enjoytrip.trip.*"%>
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
	<div class="row ms-5 me-5">
		<div>
			<div class="alert alert-primary mt-3 text-center fw-bold"
				role="alert">전국 관광지 정보</div>
			<!-- 관광지 검색 start -->
			
			<!-- kakao map start -->
			<div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
			<!-- kakao map end -->
			<div class="row" style="width: 100%; height: 400px; overflow: auto">
				<table class="table table-striped" style="display: none">
					<thead>
						<tr>
							<th>여행 순서</th>
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
			<a href="${root}/trip?action=tripPlanDelete" >초기화</a>
			<!-- 관광지 검색 end -->
		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="./assets/js/index.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5f70a755fe6b196dd3a3e0291d559aad&libraries=services,clusterer,drawing"></script>
	<script src="./assets/js/trip/userTripPlan.js"></script>
</body>
</html>
