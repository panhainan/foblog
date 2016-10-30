app.controller("TagManageController", function($uibModal,$scope,TagManageService) {
	$scope.isTagNav = true;
	setScreenAvailHeight();
	$scope.list=function(){
		TagManageService.list().then(function(data){
			console.log(data)
			$scope.tags = data.resultData;
		})
	}
	$scope.list();
});