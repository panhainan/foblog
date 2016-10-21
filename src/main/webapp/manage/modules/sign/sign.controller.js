app.controller("manageSignController",function($scope,RequestService){
	$scope.signin=function(){
		console.log($scope.authorVo);
		RequestService.postRequest("/manage/signin",$scope.authorVo,cfg_json).then(function(data){
			console.log(data);
		})
	}
})