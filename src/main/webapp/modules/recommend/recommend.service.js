app.service("RecommendService", function(RequestService) {
	this.list = function (pageNumber, pageSize) {
        return RequestService.postRequest('/recommend' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize
        	}), cfg_form);
    };
    this.hits = function(id){
        return RequestService.postRequest('/recommend/hits' ,$.param({
            "id": id
        }), cfg_form);
    }

})