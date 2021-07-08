
// 1. 로그인 되어있을 때만 삭제, 수정 버튼이 보이게 한다.
function show_btn(){
    var update_btn = document.getElementById("update_btn");
    var delete_btn = document.getElementById("delete_btn");
    var writer_uid = document.getElementById("writerUid");
    var user_uid = document.getElementById("user-uid");

    if(writer_uid.value == user_uid.value){
        update_btn.style.display = '';
        delete_btn.style.display = '';
    }
    else{
        update_btn.style.display = 'none';
            delete_btn.style.display = 'none';
    }
}

function check_update_post(){
    // write-update에 uid, writer_uid, title, contents 정보 보내주기
    var writer_uid = document.getElementById("writerUid");
        var user_uid = document.getElementById("user-uid");
        var post_uid = document.getElementById("uid");

        var form = document.getElementById("post_form");

        if(writer_uid.value == user_uid.value){
            // 수정 가능
            location.href='write-update?id=' + post_uid.value;

        }else{
            // 수정 불가능
            // 경고 창 띄우기
        }
}

function check_delete_post(){
    var writer_uid = document.getElementById("writerUid");
    var user_uid = document.getElementById("user-uid");
    var post_uid = document.getElementById("uid");

    if(writer_uid.value == user_uid.value){
        // 삭제 가능
        location.href='update/delete?id='+post_uid.value;
    }else{
        // 삭제 불가능
        // 경고 창 띄우기
    }

}

show_btn();
set_modal_title("게시글 수정 실패");