app.service("TagManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/tag', cfg_form);
    };
    this.put = function(tag){
    	return RequestService.putRequest("/manage/tag", tag, cfg_json);
    }
    this.deleteById = function(tagId){
    	return RequestService.deleteRequest("/manage/tag/"+tagId, cfg_form);
    }
	this.getArticles = function(tagId){
		return RequestService.getRequest("/manage/tag/"+tagId, cfg_form);
	}
	this.deleteActicleTag = function(tagId,articleId){
		return RequestService.deleteRequest("/manage/tag/"+tagId+"/"+articleId, cfg_form);
	}
})