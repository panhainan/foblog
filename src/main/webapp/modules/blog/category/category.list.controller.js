app.controller("CategoryListController", function($window, $location,
		$routeParams, $rootScope, $scope, CategoryService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;

	$scope.get = function() {
		CategoryService.get().then(function(data) {
			// console.log(data);
			$scope.categorys = data.resultData;
            $scope.loaded = true;
		});
	}

	$scope.init = function() {
		$scope.get();
	}
	$scope.init();
});
