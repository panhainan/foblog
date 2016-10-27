app.controller("TagArticleController",function($rootScope,$scope,TagArticleService,RightNavDataFactory){
	$scope.get = function(){
		TagArticleService.get().then(function(data){
			console.log(data);
			$rootScope.navIs = nav_tag;
			RightNavDataFactory.setVal(data.resultData);
		})
	}
	$scope.get();
});