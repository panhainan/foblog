app.controller("ArticleDetailManageController", function($scope, $routeParams,
		ArticleManageService) {
	setScreenAvailHeight();
	//console.log($routeParams.articleId)
	$scope.get = function(articleId) {
		ArticleManageService.get(articleId).then(function(data) {
			console.log(data);
			if(data.resultData!=null){
				data.resultData.content = marked(data.resultData.content)
				$scope.article = data.resultData;
			}
		});
	}
	$scope.changeToBlog= function(statusValue){
		var article={
				id:$scope.article.id,
				status:statusValue,
				onlyChangeStatus:true
		}
		ArticleManageService.put2(article).then(function(data){
//			console.log(data)
            if (data.resultCode == 1) {
                if (data.resultData > 0) {
                    $scope.get(data.resultData);
                } else {
                    alert("修改操作失败,原因如下："+data.resultMsg)
                }
            } else {
                alert("操作异常,原因如下："+data.resultMsg)
            }
		});
	}
	// 获取文章信息
	$scope.get($routeParams.articleId);
});