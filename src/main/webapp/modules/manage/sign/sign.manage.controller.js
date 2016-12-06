app.controller("SignManageController", function($scope, $rootScope,SignManageService,$location) {
	
	var doSignin = function(author) {
		SignManageService.post(author).then(function(data) {
			console.log(data);
			if (data.resultCode == 1) {
                if(data.resultData==null){
                    alert("用户名或者密码错误！");
                    $scope.user.account="";
                    $scope.user.password="";
                }else{
                    sessionStorage.setItem("token",data.resultData);
                    $location.path("/manage/info");
                }
			}else{
                alert("服务器异常，请稍后再试！");
            }
		})
	}
	$scope.signin = function() {
		$.jCryption.getKeys(web_project_name + '/manage/getKeys', function(data) {
			var keys =  data;
			var author = {
					account : "",
					password : ""
				}
				//console.log(keys)
				if (keys == null || keys == "") {
					// 没有加密串，不能登录，需要先获取加密串
					alert("服务器错误,请重试！");
					return false;
				} else {
					$.jCryption.encrypt(encodeURIComponent($scope.user.account), keys,
							function(encryptedAccount) {
								author.account = encryptedAccount;
								$.jCryption.encrypt(
										encodeURIComponent($scope.user.password), keys,
										function(encryptedPassword) {
											author.password = encryptedPassword;
//											console.log(author);
											doSignin(author);
										});
							});
				}
		});
	}
	
	

})