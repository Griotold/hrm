// 댓글 생성버튼을 변수화
const commentCreateBtn = document.querySelector("#comment-create-btn");

// 버튼 클릭 이벤트 감지
commentCreateBtn.addEventListener("click", function() {
    // 새 댓글 객체 생성
    const comment = {
        nickname: document.querySelector("#new-comment-nickname").value,
        body: document.querySelector("#new-comment-body").value,
        article_id: document.querySelector("#new-comment-article-id").value
    };
    // 댓글 객체 출력
    console.log(comment);

    // JSON 만들어서 RestController에 요청
    const url = "/api/articles/" + comment.article_id + "/comments";
    fetch(url, {
        method: "post",
        body: JSON.stringify(comment),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => {
        const msg = (response.ok) ? "댓글이 등록되었습니다." : "댓글 등록 실패!";
        alert(msg);
        window.location.reload();
    });
});
