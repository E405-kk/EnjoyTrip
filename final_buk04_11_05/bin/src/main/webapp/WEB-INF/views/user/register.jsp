<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
    crossorigin="anonymous" />
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
  <script src="../assets/js/key.js"></script>
    <!-- Core theme CSS (includes Bootstrap)-->
    <!-- <link href="../assets/css/styles.css" rel="stylesheet" /> -->
  </head>
  <body>
    <%@ include file = "/WEB-INF/views/header.jsp" %>
    <main class="row">
      <div class="col-lg-2 col-md-1"></div>
      <div class="login col-lg-8 col-md-10">
        <div class="login-title fw-bold fs-3 border-bottom pb-2 mt-2">
          회원가입
        </div>
        <form id = "form-register" method = "post" action="">
        <div class="input-id mt-3 ms-3 me-3">
            <label for="userId" class="form-label">아이디:</label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userId"
              placeholder="아이디..." />
          </div>
          <div class="input-name mt-3 ms-3 me-3">
            <label for="userName">이름:</label>
            <input
              type="text"
              class="form-control"
              id="username"
              name="userName"
              placeholder="이름..." />
          </div>
          
          <div class="input-pwd mt-3 ms-3 me-3">
            <label for="userPwd" class="d-block">비밀번호: </label>
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="userPwd"
              placeholder="비밀번호..." />
          </div>
          <div class="input-pwd-check mt-3 ms-3 me-3">
            <label for="userpwdcheck" class="d-block">비밀번호확인: </label>
            <input
              type="password"
              class="form-control"
              id="userpwdcheck"
              name="userpwdcheck"
              placeholder="비밀번호확인..." />
          </div>
          <div class="input-email mt-3 ms-3 me-3">
            <label for="userEmail">이메일:</label>
            <input
              type="email"
              name="userEmail"
              id="useremail"
              class="form-control"
              placeholder="이메일..." />
          </div>
          <div class="input-bottom mt-3 text-end border-top pt-3 me-3">
            <input
              type="button"
              class="btn btn-outline-primary fs-6 p-2"
              id="btn-submit-register"
              value="회원가입"/>
            <input
              type="reset"
              class="btn btn-outline-success fs-6 p-2"
              id="btn-reset"
              value="초기화" />
          </div>
        </form>
      </div>
      <div class="col-lg-2 col-md-1"></div>
    </main>
    <%@ include file = "/WEB-INF/views/footer.jsp" %>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    	document.querySelector("#btn-submit-register").addEventListener("click", function (){
    		if (!document.querySelector("#username").value) {
    	          alert("이름을 입력하세요.");
    	          return;
    	        } else if (!document.querySelector("#userid").value) {
    	          alert("아이디를 입력하세요.");
    	          return;
    	        } else if (!document.querySelector("#userpwd").value) {
    	          alert("비밀번호를 입력하세요.");
    	          return;
    	        } else if (document.querySelector("#userpwd").value != document.querySelector("#userpwdcheck").value) {
    	          alert("비밀번호를 다시 확인하세요.");
    	          return;
    	        } else {
    	          let form = document.querySelector("#form-register");
    	          form.setAttribute("action", "${root}/user/register");
    	          form.submit();
    	        }
    	});
    </script>
  </body>
</html>
