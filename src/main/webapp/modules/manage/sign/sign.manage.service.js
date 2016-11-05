app.service("SignManageService", function(RequestService) {
	
	this.post=function(author){
		return RequestService.postRequest("/manage/signin", author, cfg_json);
	}
})