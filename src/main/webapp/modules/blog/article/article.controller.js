app.controller("ArticleController", function($uibModal,$scope,ArticleService) {
	setScreenAvailHeight();
    $scope.currentPage = 1;
    $scope.pageSize = 3;

    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };

	$scope.list = function(currentPage,pageSize){
		ArticleService.list(currentPage,pageSize,null).then(function(data){
//			console.log(data)
			$scope.articles = data.resultData.list;
			$scope.totalItems = data.resultData.pageConfig.allCount;
		});
	}
	
	$scope.list($scope.currentPage, $scope.pageSize);
});
