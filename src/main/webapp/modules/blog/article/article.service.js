app.service("ArticleService", function(RequestService) {
	this.list = function (pageNumber, pageSize,articleStatus) {
        return RequestService.postRequest('/blog/article' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize,
        	"articleStatus":articleStatus
        	}), cfg_form);
    };
    this.get = function (code) {
        return RequestService.getRequest('/blog/article/' + code, cfg_form);
    };

    
	
	 
})