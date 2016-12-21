/**
 * Created by Pan on 2016/9/11.
 */
app.service("FriendlinkService", function ($http, $q,RequestService) {
    this.list = function () {
        return RequestService.getRequest('/friendlink', cfg_form);
    };
    this.hits = function(id){
        return RequestService.postRequest('/friendlink/hits' ,$.param({
            "id": id
        }), cfg_form);
    }
});
