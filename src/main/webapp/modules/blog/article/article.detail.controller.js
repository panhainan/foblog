app.controller("ArticleDetailController", function($scope, $routeParams,
		ArticleService) {
	setScreenAvailHeight();
	console.log($routeParams.articleTitle)
	$scope.get = function(articleTitle) {
		ArticleService.get(articleTitle).then(function(data) {
			console.log(data);
			if(data.resultData!=null){
				$scope.article = data.resultData.currentArticle;
				$scope.article.content=marked($scope.article.content);
				$scope.preArticle = data.resultData.preArticle;
				$scope.nextArticle = data.resultData.nextArticle;
			}else{
				$scope.error =true;
			}
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleTitle);
});