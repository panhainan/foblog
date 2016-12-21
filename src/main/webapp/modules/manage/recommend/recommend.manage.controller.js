app.controller("RecommendManageController", function($uibModal,$scope,RecommendManageService) {
	$scope.isRecommendNav = true;
	setScreenAvailHeight();
    $scope.currentPage = 1;
    $scope.pageSize = 11;

    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };

	$scope.list = function(currentPage,pageSize){
        RecommendManageService.list(currentPage,pageSize).then(function(data){
//			console.log(data)
			$scope.recommends = data.resultData.list;
			$scope.totalItems = data.resultData.pageConfig.allCount;
		});
	}
    $scope.list($scope.currentPage, $scope.pageSize);
    $scope.addRecommend = function(){
        var modalInstance = $uibModal.open({
            templateUrl : 'addRecommend.html',
            controller : 'addRecommendCtrl',
            backdrop : 'static',
            size : 'lg',
            resolve : {
            }
        });
        modalInstance.result.then(function(_recommend) {
            if(_recommend!=null){
                RecommendManageService.post(_recommend).then(function(data){
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.setEditRecommend = function(recommend){
        var modalInstance = $uibModal.open({
            templateUrl : 'editRecommend.html',
            controller : 'editRecommendCtrl',
            backdrop : 'static',
            size : 'lg',
            resolve : {
                recommend:function(){
                    return recommend;
                }
            }
        });
        modalInstance.result.then(function(_recommend) {
            if(_recommend!=null){
                RecommendManageService.put(_recommend).then(function(data){
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.previewRecommend = function(recommend){
        var modalInstance = $uibModal.open({
            templateUrl : 'previewRecommend.html',
            controller : 'previewRecommendCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
                recommend:function(){
                    return recommend;
                }
            }
        });
        modalInstance.result.then(function() {
            //$scope.list($scope.currentPage, $scope.pageSize);
        });
    }
    $scope.setDeleteRecommend = function(recommend){
        var modalInstance = $uibModal.open({
            templateUrl : 'deleteRecommend.html',
            controller : 'deleteRecommendCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
                recommend:function(){
                    return recommend;
                }
            }
        });
        modalInstance.result.then(function(fId) {
            if(fId!=null){
                RecommendManageService.delete(fId).then(function(data){
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
});

app.controller("deleteRecommendCtrl",function(recommend,$uibModalInstance, $scope){
    $scope.recommend =recommend;
    $scope.deleteRecommend = function(fId){
        $uibModalInstance.close(fId);
    }
    $scope.cancelDeleteRecommend = function() {
        $uibModalInstance.dismiss('cancel');
    }

});
app.controller("previewRecommendCtrl", function(recommend,$uibModalInstance, $scope) {
    $scope.recommend =recommend;
    $scope.closeRecommend = function() {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("addRecommendCtrl", function($uibModalInstance, $scope) {
    $scope.confirmAddRecommend = function(recommend){
        console.log(recommend)
        $uibModalInstance.close(recommend);
    }
    $scope.cancelAddRecommend = function() {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("editRecommendCtrl", function(recommend,$uibModalInstance, $scope) {
    $scope.recommend =recommend;
    $scope.confirmEditRecommend = function(_recommend){
        $uibModalInstance.close(_recommend);
    }
    $scope.cancelEditRecommend = function() {
        $uibModalInstance.dismiss('cancel');
    }
});

