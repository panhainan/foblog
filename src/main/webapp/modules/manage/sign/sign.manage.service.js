app.service("SignManageService", function(RequestService) {
	
	this.post=function(author){
		return RequestService.postRequest("/manage/signin", author, cfg_json);
	}

    this.delete =function(){
        return RequestService.deleteRequest("/manage/signout",cfg_form);
    }
    this.getCaptcha =function(){
        return RequestService.deleteRequest("/manage/getCaptcha",cfg_form);
    }
})