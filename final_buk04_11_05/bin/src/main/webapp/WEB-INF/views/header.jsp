<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${ pageContext.request.contextPath }" />
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="${root }">Enjoy Trip</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<c:if test="${empty userinfo}">
					<li class="nav-item"><a
						class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
						href="${ root }/user/login">로그인</a></li>
					<li class="nav-item"><a
						class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
						href="${ root }/user/register">회원가입</a></li>
				</c:if>
				<c:if test="${not empty userinfo}">
					<li class="nav-item dropdown"><a
						class="nav-link px-lg-3 py-3 py-lg-4 fs-6 dropdown-toggle"
						href="#" role="button" data-bs-toggle="dropdown">마이페이지</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="${ root }/user/manage">회원정보관리</a></li>
							<li><a class="dropdown-item"
								href="${ root }/user/logout">로그아웃</a></li>
							<li><a class="dropdown-item"
								href="${ root }/trip/userTripPlan">나의 여행 계획</a></li>
						</ul>
				</c:if>
				<li class="nav-item"><a
					class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
					href="${ root }/trip/trip">지역별 관광지</a></li>
				<li class="nav-item"><a
					class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
					href="${ root }/trip/tripPlan">여행 계획</a></li>
				<li class="nav-item"><a
					class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
					href="${root }/board/list">여행정보공유</a></li>
					<li class="nav-item"><a
					class="nav-link px-lg-3 py-3 py-lg-4 fs-6"
					href="${root }/notice/list">공지사항</a></li>
			</ul>
		</div>
	</div>
</nav>