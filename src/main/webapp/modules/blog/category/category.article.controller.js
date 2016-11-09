app.controller("CategoryArticleController", function($window, $location,
		$routeParams, $rootScope, $scope, CategoryService) {
	setScreenAvailHeight();
	$scope.getCategoryArticles = function(name) {
		CategoryService.getArtilces(name).then(function(data) {
			// console.log(data);
			$scope.categoryArticles = data.resultData;
		});
	}

	$scope.init = function() {
		if($routeParams.categoryName!=null){
			$scope.categoryName = $routeParams.categoryName;
			$scope.getCategoryArticles($scope.categoryName);
		}
		
	}

	$scope.init();

});
