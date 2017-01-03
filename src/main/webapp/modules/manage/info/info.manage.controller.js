app.controller("InfoManageController", function($scope,InfoManageService) {
	$scope.isInfoNav = true;
	setScreenAvailHeight(); 
	var getInfo = function(){
		InfoManageService.get().then(function(data){
			//console.log(data);
			if(data.resultData!=null){
                if(data.resultData.profile!=null && data.resultData.profile!=""){
                    data.resultData.profile = marked(data.resultData.profile);
                }
				$scope.info = data.resultData;
			}
		})
	}
	getInfo();
});