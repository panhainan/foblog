app.service("ArchiveService",function(RequestService){
    this.get = function () {
    	return RequestService.getRequest('/blog/archive', cfg_form);
    };
    
    this.getArtilces = function (name) {
    	return RequestService.getRequest('/blog/archive/'+name, cfg_form);
    };

});