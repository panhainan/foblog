/**
 * 
 */
app.controller("SignController",function($scope,guestSignService){
	$scope.repeatIf = false;	//记录昵称是否重复
	$scope.guestNicknameArray = [];
	//获取所有guest
	$scope.getAllGuests = function(){
		guestSignService.getAllGuests().then(function(data){
			$scope.guests = data.resultData;
		});
	};
	$scope.getAllGuests();
	//判断重复
	$scope.chargeRe = function(nickname){
		angular.forEach($scope.guests,function(item){
			$scope.guestNicknameArray.push(item.nickname);
		})
		if($scope.guestNicknameArray!=null && $scope.guestNicknameArray.indexOf($.trim(nickname))>-1){
			return true;   //重复
		}
		return false;
	};
	//登录
	$scope.guestSignin = function(){
		guestSignService.getGuestByEmail($scope.guestData.email).then(function(data){
			$scope.guest = data.resultData;
			if($scope.guest!=null){	//存在该账户
				if($scope.guest.nickname==$scope.guestData.nickname){
					//账号匹配，成功登录
					layer.msg("登录成功",{icon:6});
					//todo
				}else{
					if($scope.chargeRe($scope.guestData.nickname)){
						$scope.repeatIf = true;
						layer.alert("该昵称已存在！");
						return false;
					}else{
						layer.confirm(("确认修改昵称<em style='color:red;font-style: normal;font-weight: bold'>"+$scope.guest.nickname+"</em>为<em style='color:red;font-style: normal;font-weight: bold'>"+$scope.guestData.nickname+"</em>"),{icon:3},function(){
							$scope.guest.nickname = $scope.guestData.nickname;
							guestSignService.addOrUpdateGuest($scope.guest).then(function(data){
								if(data.resultData==1){
									layer.alert("登录成功!");
								}
							})
						},function(){
							layer.closeAll();
						})
					}
				}
			}else{	//不存在该账户
				if($scope.chargeRe($scope.guestData.nickname)){
					$scope.repeatIf = true;
					layer.alert("该昵称已存在！");
					return false;
				}else{
					guestSignService.addOrUpdateGuest($scope.guestData).then(function(data){
						if(data.resultData==1){
							layer.alert("登录成功!");
						}
					})
				}
			}
		})
	};

})