app.controller("CategoryArticleController",function($window,$location,$routeParams,$rootScope,$scope,CategoryArticleService){
	setScreenAvailHeight();
	$scope.getCategoryArticles = function(name){
		CategoryArticleService.getArtilces(name).then(function(data){
//			console.log(data);
			$scope.categoryArticles = data.resultData;
		});
	}
	$scope.selectCategory = function(name){
		$location.path("/blog/category/"+name);
	}
	
	$scope.get = function(){
		CategoryArticleService.get().then(function(data){
//			console.log(data);
			$scope.subNavDatas =  setSubNavData(data.resultData,nav_category,"分类");
			if($routeParams.categoryName!=undefined){
				$scope.categoryName = $routeParams.categoryName;
			}else if(data.resultData!=null && data.resultData.length>0){
				$scope.categoryName = data.resultData[0].name;
			}
//			console.log($scope.categoryName)
			if($scope.categoryName!=undefined){
				$scope.getCategoryArticles($scope.categoryName);
			}
		});
	}
	
	$scope.init = function(){
		$scope.get();
	}
	
	$scope.init();
	
	
});
