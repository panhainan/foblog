app.controller("ManageController", function($scope,$location) {
	setScreenAvailHeight();
    var t = sessionStorage.getItem("token")
    if(t==undefined || t=="" || t=="null" || t==null){
        $location.path("/manage/sign");
    }else{
        $location.path("/manage/info");
    }
});
