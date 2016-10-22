app.controller("ArticleManageController", function($scope,ArticleManageService) {
	console.log("ArticleManageController")
	$scope.list = function(){
		ArticleManageService.list(1,10,null).then(function(data){
			console.log(data)
			$scope.articles = data.resultData.list;
			$scope.totalItems = data.resultData.pageConfig.allCount;
		});
	}
	$scope.list();
})