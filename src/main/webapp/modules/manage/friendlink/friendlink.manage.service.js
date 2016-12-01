app.service("FriendlinkManageService", function(RequestService) {
    this.list = function (pageNumber, pageSize) {
        return RequestService.postRequest('/manage/friendlink' ,$.param({
            "pageNumber": pageNumber,
            "pageSize":pageSize
        }), cfg_form);
    };
    this.post = function (friendlink){
        return RequestService.postRequest('/manage/friendlink/save',friendlink,cfg_json);
    }
    this.put = function (friendlink){
        return RequestService.putRequest('/manage/friendlink/update',friendlink,cfg_json);
    }
    this.delete=function(fId){
        return RequestService.deleteRequest('/manage/friendlink/'+fId, cfg_form);
    }
    this.get=function(fId){
        return RequestService.getRequest('/manage/friendlink/'+fId, cfg_form);
    }
})