app.controller("ArticleDetailController", function ($scope, $routeParams,
                                                    ArticleService,MessageService) {
    setScreenAvailHeight();
    console.log($routeParams.articleTitle)
    $scope.get = function (articleTitle) {
        ArticleService.get(articleTitle).then(function (data) {
            console.log(data);
            if (data.resultData != null) {
                //data.resultData.currentArticle.content = marked(data.resultData.currentArticle.content)
                $scope.article = data.resultData.currentArticle;
                $scope.preArticle = data.resultData.preArticle;
                $scope.nextArticle = data.resultData.nextArticle;
                $scope.error = false;
            } else {
                $scope.error = true;
            }
        });
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
});