function set_modal_title(title_text){
    let modal_title = document.getElementById("modal-title");
    modal_title.innerHTML = title_text;
}

function show_modal(error_text){
    let modal_text = document.getElementById("modal_error_text");
    modal_text.innerHTML = error_text;
    $("#failedLoginModal").modal();
}