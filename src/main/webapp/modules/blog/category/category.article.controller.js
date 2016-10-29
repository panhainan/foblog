app.controller("CategoryArticleController",function($window,$location,$routeParams,$rootScope,$scope,CategoryArticleService){
	setScreenAvailHeight();
	$scope.getCategoryArticles = function(name){
		CategoryArticleService.getArtilces(name).then(function(data){
// console.log(data);
			$scope.categoryArticles = data.resultData;
		});
	}
	$scope.selectCategory = function(name){
		// 需要仔细考虑是否用这个，因为这样每次都会重新发送两次请求
		$location.path("/blog/category/"+name);
	}
	// 这里每次点击分类子栏目就会请求一次类别情况，需要优化
	//优化完毕，采用sessionStorage处理
	$scope.get = function(){
		CategoryArticleService.get().then(function(data){
// console.log(data);
			$scope.subNavDatas = setSubNavData(data.resultData,nav_category,"分类");
			sessionStorage.setItem("subNavDatas", JSON.stringify($scope.subNavDatas));
			sessionStorage.setItem("subNavType",nav_category);
			$scope.set();
		});
	}
	$scope.set = function(){
// console.log($scope.subNavDatas)
		if($routeParams.categoryName!=undefined){
			$scope.navItemName = $routeParams.categoryName;
		}else if($scope.subNavDatas.data!=null && $scope.subNavDatas.data.length>0){
			$scope.navItemName = $scope.subNavDatas.data[0].name;
		}
// console.log($scope.categoryName)
		if($scope.navItemName!=undefined){
			$scope.getCategoryArticles($scope.navItemName);
		}
	}
	
	$scope.init = function(){
// console.log(sessionStorage.getItem("subNavDatas"))
		if(sessionStorage.getItem("subNavType")==nav_category){
			if(sessionStorage.getItem("subNavDatas")!=null && sessionStorage.getItem("subNavDatas")!=""){
				$scope.subNavDatas=JSON.parse(sessionStorage.getItem("subNavDatas"));
				$scope.set();
			}
		}else{
			$scope.get();
		}
	}
	
	$scope.init();
	
/*	$scope.$on('$destroy', function() {
		sessionStorage.removeItem("subNavDatas");
    });*/
	
});
