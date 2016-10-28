app.controller("ArticleDetailController", function($scope, $routeParams,
		ArticleService) {
	setScreenAvailHeight();
	console.log($routeParams.articleTitle)
	$scope.get = function(articleTitle) {
		ArticleService.get(articleTitle).then(function(data) {
			console.log(data);
			if(data.resultData!=null){
				$scope.article = data.resultData;
				$scope.article.content=marked($scope.article.content)
			}else{
				$scope.error =true;
			}
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleTitle);
});