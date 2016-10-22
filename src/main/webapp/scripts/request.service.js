/**
 * Created by Pan on 2016/9/13.
 */
app.service("RequestService", function ($http, $q) {
    this.getRequest = function(url,paramsType){
        var deferred = $q.defer();
        $http.get(web_project_host+web_project_name+url,paramsType).success(function (data) {
            //console.log(data);
            deferred.resolve(data);
        });
        return deferred.promise;
    };
    this.postRequest = function(url,params,paramsType){
        var deferred = $q.defer();
        $http.post(web_project_host+web_project_name+url,params,paramsType).success(function (data) {
            deferred.resolve(data);
        });
        return deferred.promise;
    };
    this.putRequest = function(url,params,paramsType){
        var deferred = $q.defer();
        $http.put(web_project_host+web_project_name+url,params,paramsType).success(function (data) {
            deferred.resolve(data);
        });
        return deferred.promise;
    };
    this.deleteRequest = function(url,paramsType){
        var deferred = $q.defer();
        $http.delete(web_project_host+web_project_name + url, paramsType).success(function (data) {
            deferred.resolve(data);
        });
        return deferred.promise;
    };
});
