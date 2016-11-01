app.controller("TagArticleController",function($location,$routeParams,$rootScope,$scope,TagArticleService){
	setScreenAvailHeight();
	$scope.getTagArticles = function(name){
		TagArticleService.getArtilces(name).then(function(data){
			$scope.tagArticles = data.resultData;
		});
	}
	$scope.selectTag = function(name){
		// 需要仔细考虑是否用这个，因为这样每次都会重新发送两次请求
		$location.path("/blog/tag/"+name);
	}
	// 这里每次点击分类子栏目就会请求一次类别情况，需要优化
	//优化完毕，采用sessionStorage处理
	$scope.get = function(){
		TagArticleService.get().then(function(data){
			$scope.subNavDatas = setSubNavData(data.resultData,nav_tag,"标签");
			sessionStorage.setItem("subNavDatas", JSON.stringify($scope.subNavDatas));
			sessionStorage.setItem("subNavType",nav_tag);
			$scope.set();
		});
	}
	$scope.set = function(){
		if($routeParams.tagName!=undefined){
			$scope.navItemName = $routeParams.tagName;
		}else if($scope.subNavDatas.data!=null && $scope.subNavDatas.data.length>0){
			$scope.navItemName = $scope.subNavDatas.data[0].name;
		}
		if($scope.navItemName!=undefined){
			$scope.getTagArticles($scope.navItemName);
		}
	}
	
	$scope.init = function(){
		if(sessionStorage.getItem("subNavType")==nav_tag){
			if(sessionStorage.getItem("subNavDatas")!=null && sessionStorage.getItem("subNavDatas")!=""){
				$scope.subNavDatas=JSON.parse(sessionStorage.getItem("subNavDatas"));
				$scope.set();
			}else{
				$scope.get();
			}
		}else{
			$scope.get();
		}
	}
	
	$scope.init();
});