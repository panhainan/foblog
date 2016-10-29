app.controller("ArticleDetailManageController", function($scope, $routeParams,
		ArticleManageService) {
	setScreenAvailHeight();
	console.log($routeParams.articleId)
	$scope.get = function(articleId) {
		ArticleManageService.get(articleId).then(function(data) {
			console.log(data);
			$scope.article = data.resultData;
			$scope.article.content=marked($scope.article.content)
		});
	}
	$scope.changeToBlog= function(statusValue){
		var article={
				id:$scope.article.id,
				status:statusValue,
				onlyChangeStatus:true
		}
		ArticleManageService.put(article).then(function(data){
//			console.log(data)
			$scope.get($routeParams.articleId);
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleId);
});