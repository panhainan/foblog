app.controller("ArchiveArticleController",function($window,$location,$routeParams,$rootScope,$scope,ArchiveArticleService){
	setScreenAvailHeight();
	$scope.getArchiveArticles = function(name){
		ArchiveArticleService.getArtilces(name).then(function(data){
// console.log(data);
			$scope.archiveArticles = data.resultData;
		});
	}
	$scope.selectArchive = function(name){
		// 需要仔细考虑是否用这个，因为这样每次都会重新发送两次请求
		$location.path("/blog/archive/"+name);
	}

	$scope.get = function(){
		ArchiveArticleService.get().then(function(data){
			$scope.subNavDatas = setSubNavData(data.resultData,nav_archive,"归档");
			sessionStorage.setItem("subNavDatas", JSON.stringify($scope.subNavDatas));
			sessionStorage.setItem("subNavType",nav_archive);
			$scope.set();
		});
	}
	$scope.set = function(){
		if($routeParams.archiveName!=undefined){
			$scope.navItemName = $routeParams.archiveName;
		}else if($scope.subNavDatas.data!=null && $scope.subNavDatas.data.length>0){
			$scope.navItemName = $scope.subNavDatas.data[0].name;
		}
		if($scope.navItemName!=undefined){
			$scope.getArchiveArticles($scope.navItemName);
		}
	}
	
	$scope.init = function(){
		if(sessionStorage.getItem("subNavType")==nav_archive){
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
