/**
 * Created by Pan on 2016/12/27.
 */
app.service("ProjectManageService",function(RequestService){
    this.list = function (pageNumber, pageSize) {
        return RequestService.postRequest('/manage/project' ,$.param({
            "pageNumber": pageNumber,
            "pageSize":pageSize
        }), cfg_form);
    };
    this.post = function (project){
        return RequestService.postRequest('/manage/project/save',project,cfg_json);
    }
    this.put = function (project){
        return RequestService.putRequest('/manage/project/update',project,cfg_json);
    }
    this.delete=function(pId){
        return RequestService.deleteRequest('/manage/project/'+pId, cfg_form);
    }
    this.get=function(pId){
        return RequestService.getRequest('/manage/project/'+pId, cfg_form);
    }
});