app.controller("ArticleDetailController", function($scope, $routeParams,
		ArticleService) {
	console.log($routeParams.articleTitle)
	$scope.get = function(articleTitle) {
		ArticleService.get(articleTitle).then(function(data) {
			console.log(data);
			$scope.article = data.resultData;
			$scope.article.content=marked($scope.article.content)
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleTitle);
});