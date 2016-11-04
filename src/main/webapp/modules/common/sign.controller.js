/**
 * 
 */
app.controller("SignController",function($scope,guestSignService){
	$scope.repeatIf = false;	//记录昵称是否重复
	//获取所有guest
	$scope.getAllGuests = function(){
		guestSignService.getAllGuests().then(function(data){
			$scope.guests = data.resultData;
		});
	}
	//判断重复
	$scope.chargeRe = function(nickname){
		angular.forEach($scope.guests,function(item){
			$scope.guestNicknameArray.push(item.nickname);
		})
		if($scope.guestNicknameArray.indexOf($.trim(nickname))>-1){
			return true;   //重复
		}
		return false;
	};
	//登录
	$scope.guestSignin = function(){
		console.info($scope.guests);
		guestSignService.getGuestByEmail($scope.guestData.email).then(function(data){
			$scope.guest = data.resultData;
			if($scope.guest!=null){	//存在该账户
				if($scope.guest.nickname==$scope.guestData.nickname){
					//账号匹配，成功登录
					//todo
				}else{
					if($scope.chargeRe($scope.guestData.nickname)){
						$scope.repeatIf = true;
						/*return false;*/
					}else{
						if(confirm(("确认修改昵称"+$scope.guest.nickname+"为"+$scope.guestData.nickname+"?"))){
							//todo	修改
						}
					}
				}
			}else{	//不存在该账户
				if($scope.chargeRe($scope.guestData.nickname)){
					$scope.repeatIf = true;
					/*return false;*/
				}else{
					//todo  新增
				}
			}
		})
	};

})