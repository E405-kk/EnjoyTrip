<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Enjoy Trip</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
   
    <!-- Font Awesome icons (free version)-->
     <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"></script>
      
    <!-- Google fonts-->
      <link
      href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css" />
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
      rel="stylesheet"
      type="text/css" />
      
    <!-- Core theme CSS (includes Bootstrap)-->
      <link href="assets/css/styles.css" rel="stylesheet" />
  </head>
  <body>
    <%@ include file = "/WEB-INF/views/header.jsp" %>
    <!-- Page Header-->
    <header
      class="masthead"
      style="background-image: url('./assets/img/img_main.jpg')">
      <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
          <div class="col-md-10 col-lg-8 col-xl-7">
            <div class="site-heading">
              <h1>Enjoy Trip</h1>
              <span class="subheading">Enjoy your trip!</span>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- Main Content-->
    <div class="container-fluid">
      <div class="row gx-1 justify-content-center">
        <div class="col-lg-9 col-md-10 col-sm-11">
          <!-- Post preview-->
          <div class="post-preview"></div>
          <!-- Post preview-->
          <div class="post-preview">
            <div class="fw-bold fs-4 mb-3">인기 여행지</div>
            <!-- Divider-->
            <hr class="my-4" />
            
            <div
              class="popular-list row text-center d-flex justify-content-center">
            <c:forEach var="trip" items="${list}">
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="${trip.firstImage}"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="이미지 없음" />
                <div class="card-body mt-2">
                  <a href="#">${trip.title}</a>
                </div>
              </div>
            </c:forEach> 
            </div>
          </div>
          <!-- Divider-->
          <hr class="my-4" />
          <!-- Post preview-->
          <div class="post-preview">
            <div class="fw-bold fs-4 mb-3">인기 맛집</div>
            <!-- Divider-->
            <hr class="my-4" />
            <div
              class="popular-list row text-center d-flex justify-content-center">
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food1.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">가마골</a>
                  <div class = "fs-6 text-secondary">서울특별시 강동구 고덕로38길 63</div>
                </div>
              </div>
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food2.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">영동밀면&돼지국밥</a>
                  <div class = "fs-6 text-secondary">부산광역시 동구 중앙대로209번길 12</div>
                </div>
              </div>
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food3.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">제주돔베고기집</a>
                  <div class = "fs-6 text-secondary">제주특별자치도 제주시 월랑로4길 6</div>
                </div>
              </div>
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food4.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">경주다방</a>
                  <div class = "fs-6 text-secondary">경상북도 경주시 태종로 801</div>
                </div>
              </div>
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food5.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">가우도친구집</a>
                  <div class = "fs-6 text-secondary">전라남도 강진군 가우도길 2-7</div>
                </div>
              </div>
              <div
                class="popular-list-item card pt-3 border-1 rounded me-2 col-lg-2 col-sm-6"
                style="width: 14rem">
                <img
                  src="./assets/img/img_food6.jpg"
                  class="card-img-top"
                  style="width: 200px; height: 150px"
                  alt="..." />
                <div class="card-body mt-1">
                  <a href="#">남여주 배추된장국</a>
                  <div class = "fs-6 text-secondary">경기도 여주시 가남읍 가남로 151</div>
                </div>
              </div>
            </div>
          </div>
          <!-- Pager-->
        </div>
      </div>
    </div>
    <!-- Footer-->
    <%@ include file = "/WEB-INF/views/footer.jsp" %>
    <!-- Bootstrap core JS-->
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> 
    <!-- Core theme JS-->
    <script src="./assets/js/index.js"></script>
  </body>
</html>
