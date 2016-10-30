app.controller("ArticleDetailController", function($scope, $routeParams,
		ArticleService) {
	setScreenAvailHeight();
	console.log($routeParams.articleTitle)
	$scope.get = function(articleTitle) {
		ArticleService.get(articleTitle).then(function(data) {
			console.log(data);
			if (data.resultData != null) {
				data.resultData.currentArticle.content = marked(data.resultData.currentArticle.content)
				$scope.article = data.resultData.currentArticle;
				$scope.preArticle = data.resultData.preArticle;
				$scope.nextArticle = data.resultData.nextArticle;
			} else {
				$scope.error = true;
			}
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleTitle);
});