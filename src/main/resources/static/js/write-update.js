var noteCheckbox = document.getElementById("note-checkbox").value;

if(noteCheckbox){
    document.getElementById("note-checkbox").checked=true;
}

function is_checked(){
    let note = document.getElementById("note").value;
    if(note == 1){
        // 체크하기
        document.getElementById("note-checkbox").checked = true;
    }else if(note == 0){
        // 체크하지 않기
        document.getElementById("note-checkbox").checked = false;
    }
}

// TODO : 글을 작성하다가 세션이 만료되었을 때 글을 작성할 수 없다는 경고문 띄우기  
function check_input() {
// https://johncom.tistory.com/19
    var title = document.getElementById('title');

    if (!title.value)
    // 제목을 작성하지 않았다면
    {
        show_modal("제목을 입력해주세요!");
        title.focus();
        // 화면 커서 이동
        return;
    }
    var contents = document.getElementById('contents');

    if (!contents.value)
    {
        show_modal("내용을 입력해주세요!");
        // 화면 커서 이동
        contents.focus();
        return;
    }

    // 체크박스 체크되었는지 확인하고 값 바꿔주기
    if(document.getElementById("note-checkbox").checked){
        document.getElementById("note").value = 1;
    }else{
        document.getElementById("note").value = 0;
    }

    document.getElementById("update_write_form").submit();
    // 모두 확인 후 submit()
 }

  set_modal_title("업데이트 글 작성 실패");
  is_checked();