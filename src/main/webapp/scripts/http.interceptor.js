/**
 * Created by Pan on 2016/9/12.
 */
app.factory('HttpInterceptor', function ($q,$location) {
    return {
        request: function (config) {
//            console.log("datetime:"+$filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss')+",request url:"+config.url);
            return config;
        },

        response: function (response) {
            //这里根据服务器端返回的response是否包含response.headers('x-login') === 'true'来进行决定是否跳转到登录页面
            if (response.headers('x-login') === 'true') {
                sessionStorage.removeItem("token");
                $location.path('/manage/sign');
            }
            return response || $q.when(response);
        }
    };
});
