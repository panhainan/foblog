app.controller("TagManageController", function($uibModal, $scope,$location,
		TagManageService) {
	$scope.isTagNav = true;
	setScreenAvailHeight();
	$scope.list = function() {
		TagManageService.list().then(function(data) {
			//console.log(data)
			$scope.tags = data.resultData;
		})
	}
	$scope.editTag = function(tag) {
		var modalInstance = $uibModal.open({
			templateUrl : 'editTag.html',
			controller : 'editTagCtrl',
			backdrop : 'static',
			size : 'md',
			resolve : {
				tag : function() {
					return tag;
				}
			}
		});

		modalInstance.result.then(function(_tag) {
			if (_tag != null) {
				TagManageService.put(_tag).then(function(data) {
					console.log(data)
				});
			}
		});
	}
	$scope.editArticle = function(tag) {
		TagManageService.getArticles(tag.id).then(
				function(data) {
					// 需要判断请求是否成功，其他地方也是，暂时还没有进行处理
					//console.log(data);
					var modalInstance = $uibModal.open({
						templateUrl : 'editTagArticle.html',
						controller : 'editTagArticleCtrl',
						backdrop : 'static',
						size : 'lg',
						resolve : {
							tagArticles : function() {
								return data.resultData;
							},
							tag : function() {
								return tag;
							}
						}
					});

					modalInstance.result.then(function(articleParam) {
						if (articleParam.status == "go") {
							$location.path("/manage/article/preview/"
									+ articleParam.articleId);
						} else if (articleParam.status == "delete") {
							TagManageService.deleteActicleTag(tag.id,
									articleParam.articleId).then(
									function(data) {
										console.log(data);
										$scope.list();
									})
						}
					});
				})
	}
	$scope.deleteTag = function(tag){
		var modalInstance = $uibModal.open({
			templateUrl : 'deleteTag.html',
			controller : 'deleteTagCtrl',
			backdrop : 'static',
			size : 'md',
			resolve : {
				tag : function() {
					return tag;
				}
			}
		});

		modalInstance.result.then(function(tagId) {
			if (tagId!=null) {
				TagManageService.deleteById(tagId).then(
						function(data) {
							console.log(data);
							$scope.list();
						})
			}
		});
	}
	$scope.list();
});
app.controller("editTagCtrl", function($uibModalInstance, $scope, tag) {
	$scope.tag = tag;
	$scope.cancelEditTag = function(){
		$uibModalInstance.dismiss('cancel');
	}
	$scope.confirmEditTag = function(tag){
		var _tag = {
				id:tag.id,
				name:tag.name,
				authorId:tag.authorId,
			}
		$uibModalInstance.close(_tag);
	}
});
app.controller("editTagArticleCtrl", function($uibModal,$uibModalInstance, $scope,
		tagArticles, tag) {
	$scope.tagArticles = tagArticles;
	$scope.tag = tag;
	$scope.goArticle = function(articleId) {
		var articleParam = {
			articleId : articleId,
			status : "go"
		}
		$uibModalInstance.close(articleParam);
	}
	$scope.deleteArticle = function(articleId) {
		var articleParam = {
			articleId : articleId,
			status : "delete"
		}
		$uibModalInstance.close(articleParam);
	}
	$scope.goDeleteArticle = function(article){
		var modalInstance = $uibModal.open({
			templateUrl : 'deleteTagArticle.html',
			controller : 'deleteTagArticleCtrl',
			backdrop : 'static',
			size : 'md',
			resolve : {
				tag : function() {
					return tag;
				},
				article:function(){
					return article;
				}
			}
		});

		modalInstance.result.then(function(articleId) {
			if(articleId!=null){
				$scope.deleteArticle(articleId);
			}
		});
	}
	$scope.cancelEditArticle = function() {
		$uibModalInstance.dismiss('cancel');
	}

});
app.controller("deleteTagArticleCtrl", function($uibModalInstance, $scope, tag,article) {
	console.log(tag)
	console.log(article)
	$scope.deleteArticleTag = tag;
	$scope.deleteArticle = article;
	$scope.confirmDeleteTagArticle = function(articleId){
		$uibModalInstance.close(articleId);
	};
	$scope.cancelDeleteTagArticle=function(){
		$uibModalInstance.dismiss('cancel');
	}
})
app.controller("deleteTagCtrl",function($uibModalInstance, $scope, tag) {
	$scope.tag = tag;
	$scope.cancelDeleteTag = function(){
		$uibModalInstance.dismiss('cancel');
	}
	$scope.confirmDeleteTag = function(tagId){
		$uibModalInstance.close(tagId);
	}
})