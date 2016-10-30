app.controller("ArticleDetailManageController", function($scope, $routeParams,
		ArticleManageService) {
	setScreenAvailHeight();
	console.log($routeParams.articleId)
	$scope.get = function(articleId) {
		ArticleManageService.get(articleId).then(function(data) {
			console.log(data);
			if(data.resultData!=null){
				data.resultData.content = marked(data.resultData.content)
				$scope.article = data.resultData;
			}
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