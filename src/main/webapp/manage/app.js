var app = angular.module("manageApp", ["tm.pagination", "ngRoute", "ngSanitize",'ui.bootstrap', "ui.router", "ngFileUpload"]);
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
});
app.config(function ($routeProvider) {
	$routeProvider.when("/:username", {
        templateUrl: "manage/modules/index/index.view.html",
        controller: 'manageIndexController'
    }).when("manage/signin", {
        templateUrl: "manage/modules/sign/signin.view.html",
        controller: 'manageSignController'
    });

//    $routeProvider.otherwise({
//    	templateUrl: "manage/modules/sign/signin.view.html",
//    	controller: 'manageSignController'
//    });
});
app.run(function ($rootScope, $location, $window) {
    //$rootScope.$on("$routeChangeStart", function (event, nextRoute, currentRoute) {
    //});
});
app.controller("manageAppCtrl", function ($scope, $rootScope, $window,$uibModal) {
});


