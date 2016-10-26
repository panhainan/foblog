app.controller("ArticleManageController", function($uibModal,$scope,ArticleManageService) {
	console.log("ArticleManageController")
	$scope.list = function(){
		ArticleManageService.list(1,10,null).then(function(data){
			console.log(data)
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

		    modalInstance.result.then(function (data) {
		    	
		    });
	}
	$scope.list();
}).controller("deleteArticleCtrl", function($uibModalInstance,$scope, deleteArticle) {
	console.log(deleteArticle)
	$scope.deleteArticle = deleteArticle; 
	console.log($scope.deleteArticle)
	$scope.cancelDeleteArticle = function(){
		$uibModalInstance.close();
	}
	$scope.deleteArticle = function(articleId){
		console.log(articleId)
		$uibModalInstance.close();
	}
});