app.controller("InfoFormManageController", function($location,$scope,InfoManageService) {
	$scope.isInfoNav = true;
	setScreenAvailHeight(); 
	var getInfo = function(){
		InfoManageService.get().then(function(data){
			console.log(data);
			if(data.resultData!=null){
				$scope.info = data.resultData;
			}
		})
	}
	$scope.updateInfo = function(info){
		info.profile = $('#markdown_info_textarea').data('markdown').getContent();
		InfoManageService.put(info).then(function(data){
			console.log(data)
			if(data.resultCode==1){
				$location.path("/manage/info");
			}
		})
	}
	getInfo();
});