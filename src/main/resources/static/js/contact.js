window.onload = function(){

      $(document).ready(function(){
      var login_text = document.getElementById("login_text");
        var user_span = document.getElementById("userName");
          if(user_span.innerHTML){
              user_span.innerHTML = user_span.innerHTML + "님 로그인 중";
              login_text.innerHTML = "로그아웃 하기";
          }else{
              login_text.innerHTML = "관리자 로그인";
          }
      }
      );
  }
