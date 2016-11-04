app.service("SignManageService", function(RequestService) {
	this.getKeys = function(){
		return RequestService.getRequest("/manage/getKeys", cfg_form);
	}
})