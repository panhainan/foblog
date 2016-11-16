app.controller("ArchiveListController", function($window, $location,
		$routeParams, $rootScope, $scope, ArchiveService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
	$scope.selectArchive = function(name) {
		$location.path("/blog/archive/" + name);
	}

	$scope.get = function() {
		ArchiveService.get().then(function(data) {
			$scope.archives = data.resultData;
            $scope.loaded = true;
		});
	}
	$scope.init = function() {
		$scope.get();
	}

	$scope.init();

});
