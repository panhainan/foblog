app.controller("FriendlinkManageController", function($uibModal,$scope,FriendlinkManageService) {
	$scope.isFriendlinkNav = true;
	setScreenAvailHeight();
    $scope.currentPage = 1;
    $scope.pageSize = 11;
    $scope.list = function(currentPage, pageSize){
        FriendlinkManageService.list(currentPage, pageSize).then(function(data){
            $scope.friendlinks = data.resultData.list;
            $scope.totalItems = data.resultData.pageConfig.allCount;
        })
    };
    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };
    $scope.list($scope.currentPage, $scope.pageSize);
    $scope.addFriendlink = function(){
        var modalInstance = $uibModal.open({
            templateUrl : 'addFriendlink.html',
            controller : 'addFriendlinkCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
            }
        });
        modalInstance.result.then(function(_friendlink) {
            if(_friendlink!=null){
                FriendlinkManageService.post(_friendlink).then(function(data){
                    if(data.resultCode==0){
                        alert(data.resultMsg);
                    }
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.setEditFriendlink = function(friendlink){
        var modalInstance = $uibModal.open({
            templateUrl : 'editFriendlink.html',
            controller : 'editFriendlinkCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
                friendlink:function(){
                    return friendlink;
                }
            }
        });
        modalInstance.result.then(function(_friendlink) {
            if(_friendlink!=null){
                FriendlinkManageService.put(_friendlink).then(function(data){
                    if(data.resultCode==0){
                        alert(data.resultMsg);
                    }
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.previewFriendlink = function(friendlink){
        var modalInstance = $uibModal.open({
            templateUrl : 'previewFriendlink.html',
            controller : 'previewFriendlinkCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
                friendlink:function(){
                    return friendlink;
                }
            }
        });
        modalInstance.result.then(function() {
           //$scope.list($scope.currentPage, $scope.pageSize);
        });
    }
    $scope.setDeleteFriendlink = function(friendlink){
        var modalInstance = $uibModal.open({
            templateUrl : 'deleteFriendlink.html',
            controller : 'deleteFriendlinkCtrl',
            backdrop : 'static',
            size : 'md',
            resolve : {
                friendlink:function(){
                    return friendlink;
                }
            }
        });
        modalInstance.result.then(function(fId) {
            if(fId!=null){
                FriendlinkManageService.delete(fId).then(function(data){
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
});

app.controller("deleteFriendlinkCtrl",function(friendlink,$uibModalInstance, $scope){
    $scope.friendlink =friendlink;
    $scope.deleteFriendlink = function(fId){
        $uibModalInstance.close(fId);
    }
    $scope.cancelDeleteFriendlink = function() {
        $uibModalInstance.dismiss('cancel');
    }

});
app.controller("previewFriendlinkCtrl", function(friendlink,$uibModalInstance, $scope) {
    $scope.friendlink =friendlink;
    $scope.closeFriendlink = function() {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("addFriendlinkCtrl", function($uibModalInstance, $scope) {
    $scope.confirmAddFriendlink = function(friendlink){
        $uibModalInstance.close(friendlink);
    }
    $scope.cancelAddFriendlink = function() {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("editFriendlinkCtrl", function(friendlink,$uibModalInstance, $scope) {
    $scope.friendlink =friendlink;
    $scope.confirmEditFriendlink = function(_friendlink){
        $uibModalInstance.close(_friendlink);
    }
    $scope.cancelEditFriendlink = function() {
        $uibModalInstance.dismiss('cancel');
    }
});

