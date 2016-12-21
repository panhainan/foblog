app.controller("ArticleManageController", function($uibModal,$scope,ArticleManageService) {
	$scope.isArticleNav = true;
	setScreenAvailHeight();
//	console.log("ArticleManageController")
    $scope.currentPage = 1;
    $scope.pageSize = 11;

    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };

	$scope.list = function(currentPage,pageSize){
		ArticleManageService.list(currentPage,pageSize,null).then(function(data){
//			console.log(data)
			$scope.articles = data.resultData.list;
			$scope.totalItems = data.resultData.pageConfig.allCount;
		});
	}
	$scope.setDeleteArticle = function(article){
		var modalInstance = $uibModal.open({
		      templateUrl: 'deleteArticle.html',
		      controller: 'deleteArticleCtrl',
		      backdrop :'static',
		      size: 'md',
		      resolve: {
		    	  deleteArticle: function () {
		          return article;
		        }
		      }
		    });

		    modalInstance.result.then(function (articleId) {
//		    	console.log(articleId);
		    	ArticleManageService.delete(articleId).then(function(data){
		    		console.log(data);
		    		$scope.list($scope.currentPage, $scope.pageSize);
		    	});
		    });
	}
	$scope.list($scope.currentPage, $scope.pageSize);
});
app.controller("deleteArticleCtrl", function($uibModalInstance,$scope, deleteArticle) {
//	console.log(deleteArticle)
	$scope.theDeleteArticle = deleteArticle; 
//	console.log($scope.theDeleteArticle)
	$scope.cancelDeleteArticle = function(){
		$uibModalInstance.dismiss('cancel');
	}
	$scope.deleteArticle = function(articleId){
//		console.log(articleId)
		$uibModalInstance.close(articleId);
	}
});