var x = document.getElementById("login_message").value;

if(x){
    show_modal("아이디나 비밀번호가 잘못 되었습니다.");
}

function show_modal(error_text){
    var modal_text = document.getElementById("modal_error_text");
    modal_text.innerHTML = error_text;
    $("#failedLoginModal").modal();
}

function check_input() {
// https://johncom.tistory.com/19
    var id = document.getElementById('id');

    if (!id.value)
    // login_form 이름을 가진 form 안의 id_val 의 value가 없으면
    {
        show_modal("아이디를 입력해주세요!");
        id.focus();
        // 화면 커서 이동
        return;
    }
    var password = document.getElementById('password');

    if (!password.value)
    {
        show_modal("비밀번호를 입력해주세요!");
        // 화면 커서 이동
        password.focus();
        return;
    }

    document.getElementById("login_form").submit();
    // 모두 확인 후 submit()
 }

 set_modal_title("로그인 실패");