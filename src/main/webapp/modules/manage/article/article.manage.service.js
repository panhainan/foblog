app.service("ArticleManageService", function(RequestService) {
	this.list = function (pageNumber, pageSize,articleStatus) {
        return RequestService.postRequest('/manage/article' ,$.param({
        	"pageNumber": pageNumber,
        	"pageSize":pageSize,
        	"articleStatus":articleStatus
        	}), cfg_form);
    };
    this.look = function (id) {
        return RequestService.getRequest('/manage/article/' + id, cfg_form);
    };
    this.save = function (tbean) {
        if (tbean.id) {
            return RequestService.putRequest('/testbean/', tbean, cfg_json);
        } else {
            return RequestService.postRequest('/testbean/', tbean, cfg_json);
        }
    };
    /*
	 * this.delete = function (id) { return
	 * RequestService.deleteRequest('/testbean/' + id, cfg_form); };
	 */
})