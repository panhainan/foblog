app.service("MessageService", function(RequestService) {
    this.getGuestInfo = function (email) {
        return RequestService.postRequest('/message/guest/email', email, cfg_form);
    };

    this.comment = function(guest,messageContent,articleId){
        return RequestService.postRequest("/message",{
            email:guest.email,
            nickname:guest.nickname,
            personalWebsite:guest.personalWebsite,
            messageContent:messageContent,
            articleId:articleId
        },cfg_json);
    }
    this.list = function(articleId){
        return RequestService.getRequest("/blog/article/"+articleId+"/messages",cfg_form);
    }
	
	 
})