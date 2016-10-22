app.controller("ArticleFormManageController",function($scope,$routeParams,ArticleManageService){
	console.log($routeParams.articleId)
	$scope.get=function(articleId){
		ArticleManageService.look(articleId).then(function(data){
			console.log(data);
			$scope.article = data.resultData;
		});
	}
	$scope.get($routeParams.articleId);
	$scope.addOrUpdate=function(){
		console.log($scope.article)
	}
});