app.controller("ArticleDetailController", function ($scope, $routeParams,
                                                    ArticleService,MessageService) {
    setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
    var messageEditor;
    //console.log($routeParams.articleTitle)
    $scope.get = function (articleTitle) {
        ArticleService.get(articleTitle).then(function (data) {
            console.log(data);
            if (data.resultData != null) {
                //data.resultData.currentArticle.content = marked(data.resultData.currentArticle.content)
                $scope.article = data.resultData.currentArticle;
                $scope.preArticle = data.resultData.preArticle;
                $scope.nextArticle = data.resultData.nextArticle;
                $scope.error = false;
                $scope.loaded =true;
            } else {
                $scope.error = true;
            }
        });
    }

    $scope.comment = function(){
        //testEditor.getMarkdown();       // 获取 Markdown 源码
        //testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
        //testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        var message = messageEditor.getHTML();
        console.log(message)
    }
    $scope.getGuestInfo = function (email) {
        console.log(email);
        MessageService.getGuestInfo(email).then(function(data){
            console.log(data);
            $scope.guest =data.resultData;
        });
    }
    // 获取文章信息
    $scope.get($routeParams.articleTitle);


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
});