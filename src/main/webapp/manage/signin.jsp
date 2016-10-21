<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>管理平台-登录</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link rel="stylesheet" href="<%=basePath%>css/lib/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>css/custom/base.css" />
<script src="<%=basePath%>scripts/lib/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>scripts/lib/bootstrap.min.js"></script>
</head>

<body>
	<div class="row">
		<div class="col-md-2 col-lg-3"></div>
		<div class="col-sm-12 col-md-8 col-lg-6">
			<div class="row">
				<div style="height: 100px"></div>
				<div>
					<form class="form-horizontal" action="<%=basePath%>manage/signin" method="post">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align: center;">
								<h4 class="modal-title" id="signInModalLabel">登&emsp;&emsp;&emsp;&emsp;录</h4>
							</div>
							<div class="panel-body">
								<br />
								<div class="form-group">
									<label class="control-label col-sm-2" for="account">账户:</label>
									<div class="col-sm-10 col-md-9">
										<input type="text" class="form-control" name="account"  id="account"
											placeholder="请输入账户名">
									</div>
								</div>
								<br />
								<div class="form-group">
									<label class="control-label col-sm-2 " for="pwd">密码:</label>
									<div class="col-sm-10 col-md-9">
										<input type="password" class="form-control" name="password" id="pwd"
											placeholder="请输入账户密码">
									</div>
								</div>
								<br />
								<div class=" col-sm-offset-2 col-sm-10 col-md-9"
									style="padding:0px;">
									<button type="submit" class="btn btn-primary btn-block"
										style="margin:auto 0px;">登 录</button>
								</div>
								<br /> <br /> <br />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
