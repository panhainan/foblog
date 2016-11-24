<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html ng-app="webApp">
<head lang="zh">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>XXX的博客-v1.0</title>
    <!-- 霸下工作室 -->
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <style type="text/css">
    </style>

    <%--<link rel="stylesheet" href="plugins/editor.md/css/style.css"/>--%>
    <link rel="stylesheet" href="plugins/editor.md/css/editormd.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">

    <link rel="stylesheet" href="<%=basePath%>css/base.css"/>
    <link rel="stylesheet" href="css/left-nav.css"/>
    <link rel="stylesheet" href="css/top-nav.css"/>
    <link rel="stylesheet" href="css/left-sub-nav.css"/>
    <link rel="stylesheet" href="css/manage-left-sub-nav.css"/>
    <link rel="stylesheet" href="css/modules/blog.view.css"/>
    <link rel="stylesheet" href="css/modules/archive.view.css"/>
    <link rel="stylesheet" href="css/modules/category-tag.view.css"/>
    <link rel="stylesheet" href="css/modules/friend.view.css"/>
    <link rel="stylesheet" href="css/modules/manage.view.css"/>

    <script src="<%=basePath%>plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="<%=basePath%>plugins/jquery-jcryption/jquery.jcryption-1.1.js"></script>

    <script src="<%=basePath%>plugins/bootstrap/bootstrap.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs/angular.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs-extend-lib/angular-route.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs-extend-lib/angular-sanitize.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs-extend-lib/angular-ui-router.js"></script>
    <script src="<%=basePath%>plugins/angularjs-ui-bootstrap/ui-bootstrap-tpls-1.3.3.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs-file-upload/ng-file-upload.min.js"></script>
    <script src="<%=basePath%>plugins/angularjs-file-upload/ng-file-upload-shim.min.js"></script>

    <script src="<%=basePath%>app.js"></script>
    <script src="<%=basePath%>scripts/constant.js"></script>
    <script src="<%=basePath%>scripts/http.interceptor.js"></script>
    <script src="<%=basePath%>scripts/request.service.js"></script>

    <script src="modules/index/index.service.js"></script>
    <script src="modules/about/about.controller.js"></script>
    <script src="modules/about/about.service.js"></script>
    <script src="modules/friendlink/friendlink.controller.js"></script>
    <script src="modules/friendlink/friendlink.service.js"></script>
    <script src="modules/index/index.controller.js"></script>
    <script src="modules/common/sign.controller.js"></script>
    <script src="modules/common/sign-controller-service.js"></script>
    <script src="modules/blog/article/article.service.js"></script>
    <script src="modules/blog/article/message.service.js"></script>
    <script src="modules/blog/article/article.controller.js"></script>
    <script src="modules/blog/article/article.detail.controller.js"></script>
    <script src="modules/blog/category/category.service.js"></script>
    <script src="modules/blog/category/category.list.controller.js"></script>
    <script src="modules/blog/category/category.article.controller.js"></script>
    <script src="modules/blog/tag/tag.service.js"></script>
    <script src="modules/blog/tag/tag.list.controller.js"></script>
    <script src="modules/blog/tag/tag.article.controller.js"></script>
    <script src="modules/blog/archive/archive.service.js"></script>
    <script src="modules/blog/archive/archive.list.controller.js"></script>
    <script src="modules/blog/archive/archive.article.controller.js"></script>


</head>
<body>
<ng-include src="'<%=basePath%>modules/common/left-nav.html'"></ng-include>
<div ng-view></div>
</body>
<!-- 管理员即作者操作 -->
<script src="modules/manage/manage.controller.js"></script>
<script src="modules/manage/sign/sign.manage.service.js"></script>
<script src="modules/manage/sign/sign.manage.controller.js"></script>
<script src="modules/manage/article/article.manage.service.js"></script>
<script src="modules/manage/article/article.manage.controller.js"></script>
<script src="modules/manage/article/article.form.manage.controller.js"></script>
<script src="modules/manage/article/article.detail.manage.controller.js"></script>

<script src="modules/manage/category/category.manage.service.js"></script>
<script src="modules/manage/category/category.manage.controller.js"></script>
<script src="modules/manage/category/category.add.controller.js"></script>
<script src="modules/manage/tag/tag.manage.service.js"></script>
<script src="modules/manage/tag/tag.manage.controller.js"></script>
<script src="modules/manage/info/info.manage.service.js"></script>
<script src="modules/manage/info/info.manage.controller.js"></script>
<script src="modules/manage/info/info.form.manage.controller.js"></script>
<script src="modules/manage/friendlink/friendlink.manage.service.js"></script>
<script src="modules/manage/friendlink/friendlink.manage.controller.js"></script>


<script src="<%=basePath%>plugins/editor.md/editormd.js"></script>
<script src="<%=basePath%>plugins/editor.md/lib/marked.min.js"></script>
<script src="<%=basePath%>plugins/layer/layer.js"></script>
</html>