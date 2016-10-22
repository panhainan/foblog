<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html ng-app="manageApp">
<head>
<base href="<%=basePath%>">
<% System.out.println("basePath:"+basePath); %>
<title>管理平台</title>

<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<style type="text/css">
@import "<%=basePath%>/plugins/thinker-md/stylesheets/vendor/font.css";

@import "<%=basePath%>/plugins/thinker-md/stylesheets/vendor/markdown.css";

@import "<%=basePath%>/plugins/thinker-md/stylesheets/emoji/nature.css";

@import "<%=basePath%>/plugins/thinker-md/stylesheets/emoji/object.css";

@import "<%=basePath%>/plugins/thinker-md/stylesheets/emoji/people.css";

@import "<%=basePath%>plugins/thinker-md/stylesheets/emoji/place.css";

@import "<%=basePath%>plugins/thinker-md/stylesheets/emoji/Sysmbols.css";

@import "<%=basePath%>plugins/thinker-md/stylesheets/emoji/twemoji.css";

@import "<%=basePath%>plugins/thinker-md/stylesheets/vendor/font-awesome.css";

@import "<%=basePath%>plugins/thinker-md/stylesheets/vendor/sunburst.css";
</style>
<link rel="stylesheet" href="<%=basePath%>css/lib/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>css/custom/base.css" />



<script src="<%=basePath%>scripts/lib/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>scripts/lib/bootstrap.min.js"></script>
<script src="<%=basePath%>scripts/lib/angular-1.5.js"></script>
<script src="<%=basePath%>scripts/lib/angular-1.5.js"></script>
<script src="<%=basePath%>scripts/lib/angular-route.js"></script>
<script src="<%=basePath%>scripts/lib/angular-sanitize.js"></script>
<script src="<%=basePath%>scripts/lib/angular-ui-router.js"></script>
<script src="<%=basePath%>scripts/lib/ui-bootstrap-tpls-1.3.3.min.js"></script>
<script src="<%=basePath%>scripts/lib/tm.pagination.js"></script>
<script src="<%=basePath%>scripts/lib/ng-file-upload.js"></script>
<script src="<%=basePath%>scripts/lib/ng-file-upload-shim.js"></script>
<script src="<%=basePath%>manage/app.js"></script>
<script src="<%=basePath%>scripts/custom/constant.js"></script>
<script src="<%=basePath%>scripts/custom/http.interceptor.js"></script>
<script src="<%=basePath%>scripts/custom/request.service.js"></script>

<script src="<%=basePath%>manage/modules/index/index.controller.js"></script>
<script src="<%=basePath%>manage/modules/sign/sign.controller.js"></script>



</head>
<body ng-controller="manageAppCtrl">
	<div ng-view></div>
</body>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/underscore/underscore-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/highlight/highlight.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/he.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/marked.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/to-markdown.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/jsHtmlToText.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/tab.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/emoji.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/bootstrap-markdown.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>plugins/thinker-md/javascripts/vendor/markdown/locale/bootstrap-markdown.zh.js"></script>

</html>
