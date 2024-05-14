let register = function () {
  console.log("register");
  let name = document.getElementById("username").value;
  if (!name) {
    alert("이름을 입력하세요.");
    return;
  }

  let id = document.getElementById("userid").value;
  if (!id) {
    alert("아이디를 입력하세요.");
    return;
  }

  let pwd = document.getElementById("userpwd").value;
  let checkPwd = document.getElementById("userpwdcheck").value;
  if (!pwd) {
    alert("비밀번호를 입력하세요.");
    return;
  }
  if (pwd != checkPwd) {
    alert("비밀번호를 다시 확인하세요.");
    return;
  }

  let email = document.getElementById("useremail").value;
  if (!email) {
    alert("이메일을 입력하세요.");
    return;
  }

  // 파일로 저장하기 위해서 직렬화 후 localStorage에 저장
  let tmpData = {
    user_name: name,
    user_id: id,
    user_pwd: pwd,
    user_email: email,
  };
  localStorage.setItem("user", JSON.stringify(tmpData));

  alert("회원가입 되었습니다!");
  location.href = "./login.html";
};

let close = function () {
  location.href = "./index.html";
};

window.onload = function () {
  // localStorage.clear();
};
