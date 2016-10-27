app.factory('RightNavDataFactory', function() {
	var dataObj;
	return {
		getVal : function() {
			return dataObj;
		},
		setVal : function(data) {
			dataObj = data;
		}
	}
})