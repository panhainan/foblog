app.controller("ArchiveListController", function($window, $location,
		$routeParams, $rootScope, $scope, ArchiveService) {
	setScreenAvailHeight();
	$scope.selectArchive = function(name) {
		// 需要仔细考虑是否用这个，因为这样每次都会重新发送两次请求
		$location.path("/blog/archive/" + name);
	}

	$scope.get = function() {
		ArchiveService.get().then(function(data) {
			$scope.archives = data.resultData;
		});
	}
	$scope.init = function() {
		$scope.get();
	}

	$scope.init();

});
