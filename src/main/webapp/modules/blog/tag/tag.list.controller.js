app.controller("TagListController", function($window, $location,
		$routeParams, $rootScope, $scope, TagService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;

	$scope.get = function() {
        TagService.get().then(function(data) {
			// console.log(data);
			$scope.tags = data.resultData;
            $scope.loaded = true;
		});
	}

	$scope.init = function() {
		$scope.get();
	}
	$scope.init();
});
