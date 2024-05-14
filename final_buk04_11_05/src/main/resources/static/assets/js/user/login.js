window.onload = function () {
  // localStorage.clear();
};
let login = function () {
  let id = document.getElementById("userid").value;
  if (!id) {
    alert("아이디를 입력하세요.");
    return;
  }

  let pwd = document.querySelector("#userpwd").value;
  if (!pwd) {
    alert("비밀번호를 입력하세요.");
    return;
  }

  // 파일로 저장하기 위해서 직렬화 후 localStorage에 저장
  let tmpData = {
    user_id: id,
    user_pwd: pwd,
  };
  localStorage.setItem("login-user", JSON.stringify(tmpData));

  alert("로그인 되었습니다!");
  location.href = "../index.html";
};
