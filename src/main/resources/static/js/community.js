
// 展开二级评论
function collapseComments(e){
    // debugger
    let id = e.getAttribute("data-id");
    let comments = $('#comment-'+id);
    if (comments.hasClass("in")){
        // 折叠
        comments.removeClass("in");
    }else{
        // 展开
        console.log("展开评论")

        $.getJSON( "/comment/"+id, function( data ) {
            let comment_body = $("#comment-body-"+id);
            // comment_body.appendChild();
            var items = []
            $.each( data.data, function(comment ) {
                let c = $("<div/>",{
                    "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse comments",
                    html: comment.content
                });
                items.push(c);
            });
            $("<div/>",{
                "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comment",
                "id": "comment-"+id,
                html: items.join( "" )
            }).appendTo(comment_body);
        });
        comments.addClass("in");
    }
    e.classList.toggle("active");

}

function comment2target(targetId, type, content){
    if(!content){
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response){
            if(response.code==200){
                window.location.reload();
            }else{
                if(response.code == 2003){
                    let isAccepted = confirm(response.message);
                    if(isAccepted){
                        // 点击确认，跳转到登录页面
                        window.open("https://github.com/login/oauth/authorize?client_id=57e564c7f1f93e0fe69b&redirect_uri=http://localhost:8087/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                }else{
                    alert(response.message);
                }

            }
            console.log(response);
        },
        dataType: "json"
    });
}

// 二级评论
function comment(e){
    let commentId = e.getAttribute("data-id");
    let content = $("#input-"+commentId).val();
    comment2target(commentId, 2, content);
}


// 提交回复
function postComment(){
    let questionId = $("#question_id").val();
    let commentContent = $("#comment_content").val();
    comment2target(questionId, 1, commentContent);

}