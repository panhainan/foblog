app.controller("CategoryListController", function($window, $location,
		$routeParams, $rootScope, $scope, CategoryService) {
	setScreenAvailHeight();
	$scope.get = function() {
		CategoryService.get().then(function(data) {
			// console.log(data);
			$scope.categorys = data.resultData;
		});
	}

	$scope.init = function() {
		$scope.get();
	}
	$scope.init();
});
