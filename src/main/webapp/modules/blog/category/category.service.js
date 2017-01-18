app.service("CategoryService",function(RequestService){
    this.get = function () {
    	return RequestService.getRequest('/blog/category', cfg_form);
    };
    
    this.getArtilces = function (code) {
    	return RequestService.getRequest('/blog/category/'+code, cfg_form);
    };

});