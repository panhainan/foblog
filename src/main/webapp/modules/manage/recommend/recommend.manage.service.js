app.service("RecommendManageService", function(RequestService) {
	this.list = function (pageNumber, pageSize) {
        return RequestService.postRequest('/manage/recommend' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize
        	}), cfg_form);
    };
    this.get = function (id) {
        return RequestService.getRequest('/manage/recommend/' + id, cfg_form);
    };
    this.put = function(recommend){
    	return RequestService.putRequest('/manage/recommend/update' ,recommend, cfg_json);
    }
    this.post = function(recommend){
    	return RequestService.postRequest('/manage/recommend/save' ,recommend, cfg_json);
    }
    this.delete = function (id) {
		return RequestService.deleteRequest('/manage/recommend/' + id, cfg_form);
	};

	
	 
})