/**
 * Created by phn on 2016/2/2.
 */

var app = angular.module("webApp", ["tm.pagination", "ngRoute", "ngSanitize",'ui.bootstrap', "ui.router", "ngFileUpload"]);
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
});
app.config(function ($routeProvider) {
    $routeProvider.when("/index", {
        templateUrl: "modules/index/index.view.html",
        controller: 'IndexController'
    });

    $routeProvider.otherwise({
        templateUrl: "modules/index/index.view.html"
    });
});
app.run(function ($rootScope, $location, $window) {
    //$rootScope.$on("$routeChangeStart", function (event, nextRoute, currentRoute) {
    //});
});
app.controller("webAppCtrl", function ($scope, $rootScope, $window) {

});


