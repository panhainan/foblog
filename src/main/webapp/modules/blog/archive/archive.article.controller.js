app.controller("ArchiveArticleController", function($window, $location,
		$routeParams, $rootScope, $scope, ArchiveService) {
	setScreenAvailHeight();

	$scope.getArtilces = function(name) {
		ArchiveService.getArtilces(name).then(function(data) {
			 console.log(data);
			$scope.archiveArticles = data.resultData;
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
