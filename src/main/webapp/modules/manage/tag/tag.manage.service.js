app.service("TagManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/tag', cfg_form);
    };
	 
})