  function pushedLoginBtn(){
  var user_span = document.getElementById("userName");
    if(user_span.innerHTML){
        // logout
        console.log("logout");
        location.replace("/logout");
    }else{
        // login
        console.log("login");
        location.replace("/login");
    }
  }

var login_text = document.getElementById("login_text");
var user_span = document.getElementById("userName");
if(user_span.innerHTML){
    user_span.innerHTML = user_span.innerHTML + "님 로그인 중";
    login_text.innerHTML = "로그아웃 하기";
    setInterval(toggle, 700);
}else{
    login_text.innerHTML = "관리자 로그인";
}

// javascript
var shown = true;

function toggle() {
   if(shown) {
       user_span.style.display = 'none';
       shown = false;
   } else {
       user_span.style.display = '';
       shown = true;
   }
}
