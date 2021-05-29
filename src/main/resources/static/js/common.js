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