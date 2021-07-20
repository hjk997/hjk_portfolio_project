$(document).ready(
    function() {
    // https://extracold.tistory.com/39
        // 태그에 onchange를 부여한다.
        $('#file').change(function() {
                addPreview($(this)); //preview form 추가하기
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
                var file = input[0].files[fileIndex];
                var reader = new FileReader();
                console.log("create reader");

                let imgNum = previewIndex++;

                console.log("onload" + imgNum);
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
                console.log("finish?");
            }
        } else alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
    }


function deleteImage(idx){
    delete files[idx];
    $('#img_id_' + idx).remove();

console.log("delete");

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

//    var token = $("meta[name='_csrf']").attr("content");
//    var header = $("meta[name='_csrf_header']").attr("content");

    var data = new FormData($("#project_write_form")[0]);


//    for (var index = 0; index < Object.keys(files).length; index++) {
//                        //formData 공간에 files라는 이름으로 파일을 추가한다.
//                        //동일명으로 계속 추가할 수 있다.
//                        data.append('imageFiles',files[index]);
//                    }

    // files.forEach(index => data.append('imageFiles',files[index]));

    for(const[key] of Object.entries(files)){
        data.append('imageFiles',files[key]);
    }

    $.ajax({
        url: "project/update",
        data: data,
        processData: false,
        contentType: false,
        enctype:'multipart/form-data',
        type:"POST",
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        }).done(function (fragment){
            window.location = "project-list";
        });

 }