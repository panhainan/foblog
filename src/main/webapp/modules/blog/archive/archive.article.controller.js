app.controller("ArchiveArticleController", function($window, $location,
		$routeParams, $rootScope, $scope, ArchiveArticleService) {
	setScreenAvailHeight();

	$scope.getArtilces = function(name) {
		ArchiveArticleService.getArtilces(name).then(function(data) {
			 console.log(data);
			$scope.archiveArticles = data.resultData;
		});
	}

	$scope.init = function() {
		if($routeParams.archiveName!=null){
			$scope.getArtilces($routeParams.archiveName);
		}else{
			
		}
	}

	$scope.init();

});
