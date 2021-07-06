// 로그인 상태라면 글쓰기 버튼을 보이게 하고 아니면 안 보이게 한다
var user_span = document.getElementById("userName");
var write_post_btn = document.getElementById("write-post");
var code = document.getElementById("list");

if(user_span.innerHTML){
console.log("show");
    write_post_btn.style.display = '';
}
else{
    console.log("invisible");
    write_post_btn.style.display = 'none';
}
if(code.value == 0 || code.value == '0'){
    // invalid page range
    let error_div = document.getElementById("error-div");
    let project_list = document.getElementById("project-list");

    error_div.style.display='block';
    project_list.style.display='none';
}else{
    let error_div = document.getElementById("error-div");
    let project_list = document.getElementById("project-list");


    error_div.style.display='none';
    project_list.style.display='block';
}