window.onload = function(){
      $(document).ready(function(){
      var login_text = document.getElementById("login_text");
          if(name!="null"){
              var user_span = document.getElementById("userName");
              user_span.innerHTML = name + "님 로그인 중";
              login_text.innerHTML = "로그아웃 하기";

          }else{
              login_text.innerHTML = "관리자 로그인";
          }
      }
      );
  }

  function pushedLoginBtn(){
    if(name!="null"){
        // logout
        console.log("logout");
        location.replace("/logout");
    }else{
        // login
        console.log("login");
        location.replace("/login");
    }
  }