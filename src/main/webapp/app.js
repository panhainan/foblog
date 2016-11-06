/**
 * Created by phn on 2016/2/2.
 */

var app = angular.module("webApp", [ "tm.pagination", "ngRoute",
		'ui.bootstrap', "ngSanitize", "ui.router", "ngFileUpload" ]);
app.config(function($httpProvider) {
	$httpProvider.interceptors.push('HttpInterceptor');
});
app.filter("trusted", [ "$sce", function($sce) {
	return function(html) {
		if ((typeof html) == 'string') {
			// 判断类型为字符串
			return $sce.trustAsHtml(html);
		}
		return html;
	}
} ]);
app.config(function($routeProvider,$stateProvider) {
	  
	$routeProvider.when("/index", {
		templateUrl : "modules/index/index.view.html",
		controller : 'IndexController'
	}).when("/blog", {
		templateUrl : "modules/blog/article/article.view.html",
		controller : 'ArticleController'
	}).when("/blog/archive", {
		templateUrl : "modules/blog/archive/archive.article.view.html",
		controller : 'ArchiveArticleController'
	}).when("/blog/archive/:archiveName", {
		templateUrl : "modules/blog/archive/archive.article.view.html",
		controller : 'ArchiveArticleController'
	}).when("/blog/category", {
		templateUrl : "modules/blog/category/category.article.view.html",
		controller : 'CategoryArticleController'
	}).when("/blog/category/:categoryName", {
		templateUrl : "modules/blog/category/category.article.view.html",
		controller : 'CategoryArticleController'
	}).when("/blog/tag", {
		templateUrl : "modules/blog/tag/tag.article.view.html",
		controller : 'TagArticleController'
	}).when("/blog/tag/:tagName", {
		templateUrl : "modules/blog/tag/tag.article.view.html",
		controller : 'TagArticleController'
	}).when("/blog/article/:articleTitle", {
		templateUrl : "modules/blog/article/article.detail.view.html",
		controller : 'ArticleDetailController'
	});
	// 管理
	$routeProvider.when("/manage", {
		redirectTo:"/manage/sign"
	}).when("/manage/index", {
		templateUrl : "modules/manage/manage.view.html",
		controller : 'ManageController'
	}).when("/manage/info", {
		templateUrl : "modules/manage/info/info.manage.view.html",
		controller : 'InfoManageController'
	}).when("/manage/info/edit", {
		templateUrl : "modules/manage/info/info.form.manage.view.html",
		controller : 'InfoFormManageController'
	}).when("/manage/sign", {
		templateUrl : "modules/manage/sign/signin.manage.view.html",
		controller : 'SignManageController'
	}).when("/manage/article", {
		templateUrl : "modules/manage/article/article.manage.view.html",
		controller : 'ArticleManageController'
	}).when("/manage/article/new", {
		templateUrl : "modules/manage/article/article.form.manage.view.html",
		controller : 'ArticleFormManageController'
	}).when("/manage/article/edit/:articleId", {
		templateUrl : "modules/manage/article/article.form.manage.view.html",
		controller : 'ArticleFormManageController'
	}).when("/manage/article/preview/:articleId", {
		templateUrl : "modules/manage/article/article.detail.manage.view.html",
		controller : 'ArticleDetailManageController'
	}).when("/manage/category", {
		templateUrl : "modules/manage/category/category.manage.view.html",
		controller : 'CategoryManageController'
	}).when("/manage/tag", {
		templateUrl : "modules/manage/tag/tag.manage.view.html",
		controller : 'TagManageController'
	}).when("/manage/info", {
		templateUrl : "modules/manage/info/info.manage.view.html",
		controller : 'InfoManageController'
	}).when("/manage/friendlink", {
		templateUrl : "modules/manage/friendlink/friendlink.manage.view.html",
		controller : 'FriendlinkManageController'
	});

	$routeProvider.otherwise({
		redirectTo : '/blog'
	});
});

app.run(function($rootScope, $location, $window) {
	// $rootScope.$on("$routeChangeStart", function (event, nextRoute,
	// currentRoute) {
	// });
});
