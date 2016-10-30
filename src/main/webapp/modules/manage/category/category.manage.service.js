app.service("CategoryManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/category', cfg_form);
    };
	this.getArticles = function(categoryId){
		return RequestService.getRequest('/manage/category/'+categoryId, cfg_form);
	}
	this.update = function(category){
		return RequestService.putRequest("/manage/category", category, cfg_json);
	}
})