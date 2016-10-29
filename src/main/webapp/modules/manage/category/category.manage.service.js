app.service("CategoryManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/article/category', cfg_form);
    };
	 
})