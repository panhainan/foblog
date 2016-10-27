app.controller("CategoryArticleController",function($routeParams,$rootScope,$scope,CategoryArticleService,RightNavDataFactory){
	$scope.categoryName = $routeParams.categoryName;
	$scope.get = function(){
		CategoryArticleService.get().then(function(data){
			console.log(data);
			$rootScope.navIs = nav_category;
			RightNavDataFactory.setVal(data.resultData);
//			$scope.categorys = data.resultData;
		})
	}
	if($scope.categoryName!=undefined){
		CategoryArticleService.getArtilces($scope.categoryName).then(function(data){
			console.log(data);
			$scope.categoryArticles = data.resultData;
		})
	}else{
		$scope.get();
	}
	
	
});