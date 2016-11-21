app.service("MessageService", function(RequestService) {
    this.getGuestInfoByEmail = function (email) {
        return RequestService.postRequest('/message/guest/email',$.param({email:email}), cfg_form);
    };
    this.getGuestInfoByNickname = function (email,nickname) {
        return RequestService.postRequest('/message/guest/nickname',$.param({email:email,nickname:nickname}), cfg_form);
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