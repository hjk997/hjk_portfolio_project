var projectType = document.getElementById("projectType").value;
var gradeType = document.getElementById("gradeType").value;

if(projectType == 1){
    document.getElementById("isTeam").checked=true;
}else if(projectType == 0){
    document.getElementById("isTeam").checked=false;
}

if(gradeType){
    document.getElementById("grade"+gradeType).checked=true;
}

function check_input() {
    // 1. title
    let title = document.getElementById('title');

        if (!title.value)
        // 제목을 작성하지 않았다면
        {
            show_modal("제목을 입력해주세요!");
            title.focus();
            // 화면 커서 이동
            return;
        }
        
        // 2. summary 
    let summary = document.getElementById('summary');

    if (!summary.value)
    {
        show_modal("내용을 입력해주세요!");
        // 화면 커서 이동
        summary.focus();
        return;
    }
    
    let startedDate = document.getElementById('startedDate');
    
        if (!startedDate.value)
        {
            show_modal("시작일을 입력해주세요!");
            // 화면 커서 이동
            startedDate.focus();
            return;
        }

    let endedDate = document.getElementById('endedDate');

    if (!endedDate.value)
    {
        show_modal("종료일을 입력해주세요!");
        // 화면 커서 이동
        endedDate.focus();
        return;
    }

    let radios = document.getElementsByName("grade");
    let gradeType = document.getElementById("gradeType");
    for (let i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            // get value, set checked flag or do whatever you need to
            gradeType.value = radios[i].value;
            break;
        }
    }

    let isTeam = document.getElementById("isTeam").checked;
    let projectType = document.getElementById("projectType");
    if(isTeam){
        projectType.value = 1;
    }else{
        projectType.value = 0;
    }

    document.getElementById("project_write_form").submit();
    // 모두 확인 후 submit()
 }