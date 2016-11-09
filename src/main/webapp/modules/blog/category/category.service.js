app.service("CategoryService",function(RequestService){
    this.get = function () {
    	return RequestService.getRequest('/blog/category', cfg_form);
    };
    
    this.getArtilces = function (name) {
    	return RequestService.getRequest('/blog/category/'+name, cfg_form);
    };

});