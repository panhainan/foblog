/**
 * Created by Pan on 2016/9/12.
 */
app.factory('HttpInterceptor', function ($q, $filter) {
    return {
        request: function (config) {
            console.log("datetime:"+$filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss')+",request url:"+config.url);
            return config;
        },

        response: function (response) {
            return response || $q.when(response);
        }
    };
});