<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Find Password</title>
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
  	<c:if test="${msg ne null }">
  		<script>
  			alert("${msg}");
  		</script>
  	</c:if>
    <%@ include file = "/WEB-INF/views/header.jsp" %>
    <main class="row">
      <div class="col-lg-2 col-md-1"></div>
      <div class="login col-lg-8 col-md-10">
        <div class="login-title fw-bold fs-3 border-bottom pb-2 mt-2">
          비밀번호 찾기
        </div>
        <form id = "form-findpwd" method = "post" action="${root}/user">
        <input type = "hidden" name = "action" value = "findpwd">
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
          
          <div class="input-bottom mt-3 text-end border-top pt-3 me-3">
            <input
              type="submit"
              class="btn btn-outline-success fs-6 p-2"
              id="btn-findpwd"
              value="비밀번호 찾기" />
          </div>
        </form>
      </div>
      <div class="col-lg-2 col-md-1"></div>
    </main>
    <%@ include file = "/WEB-INF/views/footer.jsp" %>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
 	<script>
		document.querySelector("#btn-findpwd").addEventListener("click", function(){
			if (!document.querySelector("#userid").value) {
		          alert("아이디를 입력하세요.");
		          return;
		        } else if (!document.querySelector("#username").value) {
		          alert("이름을 입력하세요.");
		          return;
		        } else {
		          let form = document.querySelector("#form-findpwd");
		          form.setAttribute("action", "${root}/user/findpwd");
		          form.submit();
		        }
		});

	</script> 
  </body>
</html>
