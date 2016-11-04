/**
 * 
 */
app.controller("SignController",function($scope,guestSignService){
	$scope.guestSignin = function(){
		$scope.guest = guestSignService.getGuestByEmail($scope.guest.email);
	};
})