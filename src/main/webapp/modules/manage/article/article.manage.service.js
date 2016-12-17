app.service("ArticleManageService", function(RequestService) {
	this.list = function (pageNumber, pageSize,articleStatus) {
        return RequestService.postRequest('/manage/article' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize,
        	"articleStatus":articleStatus
        	}), cfg_form);
    };
    this.get = function (id) {
        return RequestService.getRequest('/manage/article/' + id, cfg_form);
    };
    this.put = function(article){
    	return RequestService.putRequest('/manage/article/update' ,article, cfg_json);
    }
    this.put2 = function(article){
        return RequestService.putRequest('/manage/article/draft2article' ,article, cfg_json);
    }
    this.post = function(article){
    	return RequestService.postRequest('/manage/article/save' ,article, cfg_json);
    }
    this.delete = function (id) {
		return RequestService.deleteRequest('/manage/article/' + id, cfg_form); 
	};
    this.getCategorys = function () {
    	return RequestService.getRequest('/manage/category', cfg_form);
    };
    this.getTags = function () {
    	return RequestService.getRequest('/manage/tag', cfg_form);
    };
    
	
	 
})