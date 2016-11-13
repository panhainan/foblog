app.service("MessageService", function(RequestService) {
    this.getGuestInfo = function (email) {
        return RequestService.getRequest('/guest/' + email, cfg_form);
    };

    
	
	 
})