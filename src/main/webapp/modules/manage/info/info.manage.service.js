app.service("InfoManageService", function(RequestService) {
    this.get = function () {
    	return RequestService.getRequest('/manage/info', cfg_form);
    };
	 
})