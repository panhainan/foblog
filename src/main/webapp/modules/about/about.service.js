/**
 * Created by Pan on 2016/9/11.
 */
app.service("AboutService", function (RequestService) {
    this.get = function () {
        return RequestService.getRequest('/author', cfg_form);
    };
});
