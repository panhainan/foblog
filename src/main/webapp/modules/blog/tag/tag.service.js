app.service("TagService",function(RequestService){
    this.get = function () {
    	return RequestService.getRequest('/blog/tag', cfg_form);
    };
    this.getArtilces = function (name) {
    	return RequestService.getRequest('/blog/tag/'+name, cfg_form);
    };

});