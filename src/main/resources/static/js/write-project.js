$(document).ready(
    function() {
    // https://extracold.tistory.com/39
        // 태그에 onchange를 부여한다.
        $('#file').change(function() {
                addPreview($(this)); //preview form 추가하기
                $("#file").val("");
        });

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
    });

    var preview = document.getElementById('preview');
    var files = {};
    var previewIndex = 0;

    // image preview 기능 구현
    // input = file object[]
    function addPreview(input) {
        if (input[0].files) {
            //파일 선택이 여러개였을 시의 대응
            for (var fileIndex = 0 ; fileIndex < input[0].files.length ; fileIndex++) {
            if(Object.keys(files).length > 19){
                break;
            }
                var file = input[0].files[fileIndex];

                var file_kind = file.name.lastIndexOf('.');
                var file_name = file.name.substring(file_kind+1, file.name.length);
                var file_type = file_name.toLowerCase();

	            var check_file_type=new Array();

	            check_file_type=['jpg','gif','png'];
                if(check_file_type.indexOf(file_type)==-1) {
                    continue;
                }

                var reader = new FileReader();

                let imgNum = previewIndex++;

                // div id="preview" 내에 동적코드추가.
                // 이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
                let span = document.createElement('span');
                span.id = "img_id_" + imgNum;
                span.style.width = '100px';
                span.style.height = '100px';
                preview.appendChild(span);

                let image = document.createElement("img");
                //image.src = img.target.result;
                image.style.width='inherit';
                image.style.height='inherit';
                image.style.cursor='pointer';
                image.setAttribute('onclick', 'deleteImage('+imgNum+')');

                //image.onclick = () => deleteImage(imgNum);
                span.appendChild(image);

                files[imgNum] = file;

                reader.onloadend = (function(aImg) {
                    return function(e) {
                        aImg.src = e.target.result;
                    };
                })(image);

                reader.readAsDataURL(file);
            }
        } else alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.

    checkNumOfFiles();
    }


function deleteImage(idx){
    delete files[idx];
    $('#img_id_' + idx).remove();

    checkNumOfFiles();
}

function checkNumOfFiles(){
    // 첨부 파일 개수가 20개를 넘으면 파일 버튼을 활성화하지 못하게 한다.
    if(Object.keys(files).length > 19){
        $("#file_label").text("더 이상 파일을 첨부할 수 없습니다.");
        $("#file").attr("disabled", true).attr("readonly", true);
    }else{
        $("#file_label").text("이미지 첨부하기");
        $("#file").attr("disabled", false).attr("readonly", false);
    }
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

    var data = new FormData($("#project_write_form")[0]);

    for(const[key] of Object.entries(files)){
        data.append('imageFiles',files[key]);
    }

    $.ajax({
        url: "project/update",
        data: data,
        processData: false,
        contentType: false,
        enctype:'multipart/form-data',
        type:"POST"
        }).done(function (fragment){
            window.location = "project-list";
        });

 }