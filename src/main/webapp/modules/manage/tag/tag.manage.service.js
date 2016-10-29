app.service("TagManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/article/tag', cfg_form);
    };
	 
})