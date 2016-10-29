app.service("FriendlinkManageService", function(RequestService) {
    this.list = function () {
    	return RequestService.getRequest('/manage/friendlink', cfg_form);
    };
	 
})