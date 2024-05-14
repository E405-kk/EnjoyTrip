<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<script src="../assets/js/key.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<!-- <link href="../assets/css/styles.css" rel="stylesheet" /> -->
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>
	<main class="row">
		<div class="col-lg-2 col-md-1"></div>
		<div class="login col-lg-8 col-md-10">
			<div class="login-title fw-bold fs-3 border-bottom pb-2 mt-2">
				마이페이지</div>
				<div class="input-id mt-3 ms-3 me-3">
					<label for="userId" class="form-label">아이디:</label> <input
						type="text" class="form-control" id="userid" name="userId"
						value="${userinfo.userId }" readonly />
				</div>
				<div class="input-name mt-3 ms-3 me-3">
					<label for="userName">이름:</label> <input type="text"
						class="form-control" id="username" name="userName"
						value="${userinfo.userName }" readonly />

				</div>

				<div class="input-email mt-3 ms-3 me-3">
					<label for="userEmail">이메일:</label> <input type="email"
						name="useremail" id="userEmail" class="form-control"
						value="${userinfo.userEmail }" readonly />

				</div>

				<div class="input-bottom mt-3 text-end border-top pt-3 me-3">
					<button class="btn btn-outline-primary fs-6 p-2" id="btn-mvmodify">회원정보수정</button>
					<button class="btn btn-outline-success fs-6 p-2" id="btn-remove">회원탈퇴</button>

				</div>
		</div>
		<div class="col-lg-2 col-md-1"></div>
	</main>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script>
		document.querySelector("#btn-mvmodify").addEventListener("click",
				function() {
					var pwd = prompt("비밀번호를 입력하세요.", "비밀번호 입력");
					console.log(pwd);
					console.log("${userinfo.userPwd}");

					if (pwd == "${userinfo.userPwd}") {
						location.href = "${root}/user/modify";
					} else {
						alert("비밀번호가 일치하지 않습니다.");
						return;
					}
				});
		document
				.querySelector("#btn-remove")
				.addEventListener(
						"click",
						function() {
							if (confirm("회원 탈퇴하시겠습니까?")) {
								location.href = "${root}/user/remove";
							} else {
								return;
							}
						});
	</script>

</body>
</html>