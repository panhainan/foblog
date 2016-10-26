app.controller("ArticleDetailManageController", function($scope, $routeParams,
		ArticleManageService) {
	console.log($routeParams.articleId)
	$scope.get = function(articleId) {
		ArticleManageService.get(articleId).then(function(data) {
			console.log(data);
			$scope.article = data.resultData;
			$scope.article.content=marked($scope.article.content)
			$scope.selectTagIds = $scope.article.tagIds;
		});
		// $scope.getTypes();
		$scope.getCategorys();
		$scope.getTags();
	}
	$scope.getTypes = function() {
		ArticleManageService.getTypes().then(function(data) {
		});
	}
	$scope.getCategorys = function() {
		ArticleManageService.getCategorys().then(function(data) {
			$scope.categorys = data.resultData;
		});
	}
	$scope.getTags = function() {
		ArticleManageService.getTags().then(function(data) {
			$scope.tags = data.resultData;
			$scope.selectedTagNames = new Array();
			var s = $scope.selectTagIds;
			if(s!=undefined && s!="" ){
				var tagIdArr = s.split(",");
				for (var j = 0; j < tagIdArr.length; j++) {
					for (var i = 0; i < $scope.tags.length; i++) {
						if (tagIdArr[j] == $scope.tags[i].id) {
							$scope.selectedTagNames.push($scope.tags[i].name);
							$scope.tags[i].isCheck=true;
						}
					}
				}
			}
//			console.log($scope.selectedTagNames);
		});
	}
	
	// 获取文章信息
	$scope.get($routeParams.articleId);
});