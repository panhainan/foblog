app.controller("CategoryArticleController", function($window, $location,
		$routeParams, $rootScope, $scope, CategoryService) {
	setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;

	$scope.getCategoryArticles = function(name) {
		CategoryService.getArtilces(name).then(function(data) {
			// console.log(data);
            if(data.resultData.category!=null){
                $scope.category = data.resultData.category;
                $scope.categoryArticles = data.resultData.listArticle;
            }else{
                $scope.noSuchCategory = true;
            }
            $scope.loaded = true;
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
