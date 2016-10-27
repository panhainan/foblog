app.service("ArticleService", function(RequestService) {
	this.list = function (pageNumber, pageSize,articleStatus) {
        return RequestService.postRequest('/blog/article' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize,
        	"articleStatus":articleStatus
        	}), cfg_form);
    };
    this.get = function (title) {
        return RequestService.getRequest('/blog/article/' + title, cfg_form);
    };
    this.getCategorys = function () {
    	return RequestService.getRequest('/blog/article/category', cfg_form);
    };
    this.getTags = function () {
    	return RequestService.getRequest('/blog/article/tag', cfg_form);
    };
    
	
	 
})