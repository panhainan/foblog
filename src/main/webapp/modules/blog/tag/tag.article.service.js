app.service("TagArticleService",function(RequestService){
    this.get = function () {
    	return RequestService.getRequest('/blog/tag', cfg_form);
    };

});