app.controller("ArchiveArticleController", function($window, $location,
		$routeParams, $rootScope, $scope, ArchiveService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;
	$scope.getArtilces = function(name) {
		ArchiveService.getArtilces(name).then(function(data) {
			 console.log(data);
			$scope.archiveArticles = data.resultData;
            $scope.loaded = true;
		});
	}

	$scope.init = function() {
		if($routeParams.archiveName!=null){
			$scope.archiveName = $routeParams.archiveName;
			$scope.getArtilces($scope.archiveName);
		}else{
			
		}
	}

	$scope.init();

});
