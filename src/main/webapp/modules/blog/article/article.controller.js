app.controller("ArticleController", function($rootScope,$routeParams,$location,$uibModal,$scope,ArticleService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
    var p = $location.search().page;
    $scope.currentPage = p?p:1;
    //console.info($scope.currentPage)
    $scope.pageSize = 10;

    $scope.selectedValue = "/blog"

    $scope.goUrl = function(url){
        //console.log(url)
        $location.path(url)
    }
    $scope.pageChanged = function(currentPage) {
        //console.log('Page changed to: ' + $scope.currentPage);
        $location.search({page:currentPage});
        //$scope.list($scope.currentPage, $scope.pageSize);
    };

	$scope.list = function(currentPage,pageSize){
        //console.log(currentPage)
        if(currentPage>$scope.totalItems || currentPage<=0){
            $location.search({page:1});
        }else{
            ArticleService.list(currentPage,pageSize,null).then(function(data){
                //console.log(data)
                if(data.resultCode==1){
                    $scope.articles = data.resultData.list;
                    $scope.totalItems = data.resultData.pageConfig.allCount;
                    $scope.currentPage=currentPage;
                    $scope.loaded = true;
                }else{
                    $scope.error = true;
                    $scope.errorMessage = data.resultMsg;
                    $scope.loaded = true;
                }
            });
        }
	}
	
	$scope.list($scope.currentPage, $scope.pageSize);
});
