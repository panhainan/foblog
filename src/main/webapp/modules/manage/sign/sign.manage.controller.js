app.controller("SignManageController", function($scope, SignManageService,$location) {
	
	var doSignin = function(author) {
		SignManageService.post(author).then(function(data) {
			console.log(data);
			if (data.resultCode == 1) {
				$location.path("/manage/index");
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
				console.log(keys)
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