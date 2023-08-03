
function postComment(){
    let questionId = $("#question_id").val();
    let commentContent = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": commentContent,
            "type": 1
        }),
        success: function (response){
            if(response.code==200){
                $("#comment_section").hide();
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