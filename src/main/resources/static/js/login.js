  window.onload = function(){
    var x = document.getElementById("login_message").value;
    console.log(x)
    if(x){
      $(document).ready(function(){
          $("#failedLoginModal").modal();
      });
    }
  }