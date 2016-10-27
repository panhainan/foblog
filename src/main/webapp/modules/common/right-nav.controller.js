app.controller("RightNavController", function($scope, RightNavDataFactory) {
	$scope.navDatas = {
			data:new Array(),
			typeCode:"",
			typeName:""
	}
	$scope.$watch("navIs", function(newValue, oldValue) {
		console.log(newValue);
		console.log(RightNavDataFactory.getVal());
		if (newValue == nav_category) {
			$scope.navDatas.typeName = "分类";
		} else if (newValue == nav_tag) {
			$scope.navDatas.typeName = "标签";
		}
		if (RightNavDataFactory.getVal() != null) {
			$scope.navDatas.typeCode = newValue;
			$scope.navDatas.data = RightNavDataFactory.getVal();
		}
	});

})