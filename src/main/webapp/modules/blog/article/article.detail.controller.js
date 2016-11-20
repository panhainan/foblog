app.controller("ArticleDetailController", function ($scope, $routeParams,
                                                    ArticleService,MessageService) {
    setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
    $(function () {
        messageEditor = editormd("message-editormd", {
            width: "100%",
            height: 180,
            watch:false,
            path: web_project_name + "/plugins/editor.md/lib/",
            toolbarIcons: function () {
                return editormd.toolbarModes["mini"];
            },
            toolbar  : false,             //关闭工具栏
            saveHTMLToTextarea: true,
            lineNumbers:false,
            styleSelectedText:false,
            styleActiveLine:false,
            autoFocus:false,
            toolbarAutoFixed:false
        });
    });
    var messageEditor;
    //console.log($routeParams.articleTitle)
    $scope.get = function (articleTitle) {
        ArticleService.get(articleTitle).then(function (data) {
            console.log(data);
            if(data.resultCody==0){
                $scope.error=true;
                $scope.errorMessage = "服务器错误";
            }else{
                if (data.resultData != null) {
                    //data.resultData.currentArticle.content = marked(data.resultData.currentArticle.content)
                    data.resultData.currentArticle.content = marked(data.resultData.currentArticle.content)
                    $scope.article = data.resultData.currentArticle;
                    $scope.preArticle = data.resultData.preArticle;
                    $scope.nextArticle = data.resultData.nextArticle;
                    $scope.error = false;
                    $scope.loaded =true;
                    MessageService.list($scope.article.id).then(function(data){
                        console.log(data)
                        if(data.resultCode==0){
                            $scope.errorMessage = "服务器异常，没有获取到评论信息！"
                        }else{
                            $scope.messages = data.resultData;
                        }
                    })
                } else {
                    $scope.error=true;
                    $scope.errorMessage = "文章不存在";
                }
            }
        });
    }

    $scope.comment = function(){

        //testEditor.getMarkdown();       // 获取 Markdown 源码
        //testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
        //testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        var message = messageEditor.getHTML();
        if(message.trim()==""){
            $scope.resultMessage = "请输入你的观点！";
            $("#message-editormd").css("border-color","red");
            return false;
        }
        //console.log(message)
        //console.log($scope.guest);
        MessageService.comment($scope.guest,message,$scope.article.id).then(function(data){
            console.log(data)
            if(data.resultCode==0){
                $scope.resultMessage = data.resultMessage;
            }else{
                if(data.resultData==false){
                    $scope.resultMessage = data.resultMessage;
                }else{
                    messageEditor.setValue("");
                    //列出刚刚评论的信息
                    MessageService.list($scope.article.id).then(function(data){
                        if(data.resultCode==0){
                            $scope.errorMessage = "服务器异常，没有获取到评论信息！"
                        }else{
                            $scope.messages = data.resultData;
                        }
                    })
                }

            }
        })
    }
    $scope.getGuestInfo = function (email) {
        $("#message-editormd").css("border-color","#ddd");
        //console.log(email);
        if(email==null || email.trim()==""){
            return false;
        }
        MessageService.getGuestInfo(email).then(function(data){
            //console.log(data);
            $scope.guest =data.resultData;
        });
    }
    // 获取文章信息
    $scope.get($routeParams.articleTitle);



});