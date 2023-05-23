/**
* 게시판 작성, 수정 양식 공통 스크립트
*
*/

window.addEventListener("DOMContentLoaded", function() {
    try {
        CKEDITOR.replace("content", {
            height: 350,
        });
    } catch(e) {}
});
