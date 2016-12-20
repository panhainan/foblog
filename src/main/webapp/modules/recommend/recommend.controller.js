app.controller("RecommendController", function($uibModal,$scope,RecommendService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
    $scope.currentPage = 1;
    $scope.pageSize = 10;

    $scope.hit=function(articleUrl,id){
        RecommendService.hits(id).then(function(data){
//			console.log(data)
        });
        window.open(articleUrl);
    }
    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };

	$scope.list = function(currentPage,pageSize){
        RecommendService.list(currentPage,pageSize).then(function(data){
//			console.log(data)
			$scope.recommends = data.resultData.list;
			$scope.totalItems = data.resultData.pageConfig.allCount;
            $scope.loaded = true;
		});
	}
	
	$scope.list($scope.currentPage, $scope.pageSize);
});
