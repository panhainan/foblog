app.controller("SignManageController",function($scope,SignManageService){
	var keys=null;
	var getKeys = function(){
		SignManageService.getKeys().then(function(data){
			console.log(data);
			keys = data.resultData;
		})
	}
	var doSignin = function(author){
		RequestService.postRequest("/manage/signin",author,cfg_json).then(function(data){
			console.log(data);
			if(resultCode.resultCode==1){
				
			}
		})
	}
	
	$scope.signin=function(){
		console.log($scope.user);
		var author = {
				account:"",
				password:""
		}
		if(keys==null || keys==""){
			//没有加密串，不能登录，需要先获取加密串
			return false;
		}else{
			$.jCryption.encrypt($scope.user.account, keys, function(encryptedAccount) {
				author.account = encryptedAccount;
				$.jCryption.encrypt($scope.user.password, keys, function(encryptedPassword) {
					author.password = encryptedPassword;
					console.log(author);
//					doSignin(author);
				});
			});
		}
	}
	
	//进入登录页面，获取加密串
	getKeys()
})