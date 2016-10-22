app.controller("SignManageController",function($scope,RequestService){
	$scope.signin=function(){
		console.log($scope.authorVo);
		RequestService.postRequest("/manage/signin",$scope.authorVo,cfg_json).then(function(data){
			console.log(data);
			if(resultCode.resultCode==1){
				
			}
		})
	}
})