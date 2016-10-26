/**
 * Created by phn on 2016/2/2.
 */

var app = angular.module("webApp", ["tm.pagination", "ngRoute",'ui.bootstrap', "ngSanitize", "ui.router", "ngFileUpload"]);
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
});
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "modules/index/index.view.html",
        controller: 'IndexController'
    });
//    管理
    $routeProvider.when("/manage", {
        templateUrl: "modules/manage/sign/signin.manage.view.html",
        controller: 'SignManageController'
    }).when("/manage/article", {
        templateUrl: "modules/manage/article/article.manage.view.html",
        controller: 'ArticleManageController'
    }).when("/manage/article/new",{
    	templateUrl:"modules/manage/article/article.form.manage.view.html",
    	controller: 'ArticleFormManageController'
    }).when("/manage/article/edit/:articleId",{
    	templateUrl:"modules/manage/article/article.form.manage.view.html",
    	controller: 'ArticleFormManageController'
    }).when("/manage/article/preview/:articleId",{
    	templateUrl:"modules/manage/article/article.detail.manage.view.html",
    	controller: 'ArticleDetailManageController'
    });

    $routeProvider.otherwise({
    	redirectTo: '/'
    });
});
app.filter("trusted", ["$sce", function ($sce) {
    return function (html) {
        if (typeof html== 'string')   //判断类型为字符串
            return $sce.trustAsHtml(html);  
        return html;
    }
}]);
app.run(function ($rootScope, $location, $window) {
    //$rootScope.$on("$routeChangeStart", function (event, nextRoute, currentRoute) {
    //});
});



