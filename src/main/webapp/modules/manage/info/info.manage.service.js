app.service("InfoManageService", function(RequestService) {
    this.get = function () {
    	return RequestService.getRequest('/manage/author', cfg_form);
    };
	this.put = function(info){
		return RequestService.putRequest("/manage/author", info, cfg_json)
	}
})