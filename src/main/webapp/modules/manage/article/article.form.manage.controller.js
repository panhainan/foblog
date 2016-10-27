app.controller("ArticleFormManageController", function($scope,$location, $routeParams,
		ArticleManageService) {
	console.log($routeParams.articleId)
	$scope.get = function(articleId) {
		ArticleManageService.get(articleId).then(function(data) {
			console.log(data);
			$scope.article = data.resultData;
			$scope.article.content=toMarkdown($scope.article.content)
			console.log($scope.article.content)
			$scope.selectTagIds = $scope.article.tagIds;
		});
	}
	$scope.getTypes = function() {
		ArticleManageService.getTypes().then(function(data) {

		});
	}
	$scope.getCategorys = function() {
		ArticleManageService.getCategorys().then(function(data) {
			$scope.categorys = data.resultData;
		});
	}
	$scope.getTags = function() {
		ArticleManageService.getTags().then(function(data) {
//			console.log(data);
			$scope.tags = data.resultData;
			$scope.selectedTagNames = new Array();
			var s = $scope.selectTagIds;
//			console.log($scope.selectTagIds)
//			console.log(s)
			if(s!=undefined && s!="" ){
				var tagIdArr = s.split(",");
				for (var j = 0; j < tagIdArr.length; j++) {
					for (var i = 0; i < $scope.tags.length; i++) {
						if (tagIdArr[j] == $scope.tags[i].id) {
							$scope.selectedTagNames.push($scope.tags[i].name);
							$scope.tags[i].isCheck=true;
						}
					}
				}
			}
//			console.log($scope.selectedTagNames);
		});
	}
	$scope.$watch('tagName', function(newValue, oldValue) {
		if(newValue!="" && newValue!=undefined){
			var flagIndex = newValue.indexOf(";");
			flagIndex = flagIndex>=0?flagIndex:newValue.indexOf("；");
			if(flagIndex>=0){
				if(flagIndex>0){
					var newName = newValue.substring(0,flagIndex);
					var existFlag = false;
					for(var i=0;i<$scope.selectedTagNames.length;i++){
						if($scope.selectedTagNames[i].toLowerCase()==newName.toLowerCase()){
							existFlag = true;
							break;
						}
					}
					if(!existFlag){
						for (var i = 0; i < $scope.tags.length; i++) {
							if (newName.toLowerCase() == $scope.tags[i].name.toLowerCase()) {
								$scope.tags[i].isCheck=true;
							}
						}
						$scope.selectedTagNames.push(newName);
					}
				}
				$scope.tagName="";
			}
		}
		
	});
	$scope.deleteTagName = function(tName){
		var deleteIndex =-1;
		for(var i=0;i<$scope.selectedTagNames.length;i++){
			if($scope.selectedTagNames[i].toLowerCase()==tName.toLowerCase()){
				deleteIndex = i;
				break;
			}
		}
		if(deleteIndex>=0){
			$scope.selectedTagNames.splice(deleteIndex,1);
			for (var i = 0; i < $scope.tags.length; i++) {
				if (tName.toLowerCase() == $scope.tags[i].name.toLowerCase()) {
					$scope.tags[i].isCheck=false;
				}
			}
		}
		
	}
	$scope.isSelectExists = false;
	$scope.selectExists = function() {
		$scope.isSelectExists = true;
	}
	$scope.confirmSelectExists = function(status) {
		if(status==1){
			$scope.isSelectExists = false;
		}else{
			var selectedTags = new Array();
			$(".tagsCheckbox input[type='checkbox']:checked").each(function() {
				for (var i = 0; i < $scope.tags.length; i++) {
					if (this.title == $scope.tags[i].name) {
						$scope.tags[i].isCheck=true;
					}
				}
				selectedTags.push(this.title)
			})
			
			for(var j=0;j<selectedTags.length;j++){
				var existFlag = false;
				for(var i=0;i<$scope.selectedTagNames.length;i++){
					if($scope.selectedTagNames[i].toLowerCase()==selectedTags[j].toLowerCase()){
						existFlag = true;
						break;
					}
				}
				if(!existFlag){
					$scope.selectedTagNames.push(selectedTags[j]);
				}
			}
//			console.log($scope.selectedTagNames);
			$scope.isSelectExists = false;
		}
		
	}

	
	$scope.saveOrUpdate = function(status) {
		$scope.article.status = status;
		$scope.article.tagNames=$scope.selectedTagNames;
		console.log($('#markdown_textarea').data('markdown').parseContent())
		console.log(marked($('#markdown_textarea').data('markdown').getContent()));
		console.log($('#markdown_textarea').data('markdown').getContent());

		$scope.article.content = $('#markdown_textarea').data('markdown').getContent();//$('#markdown_textarea').data('markdown').parseContent();
		console.log($scope.article);
		if($scope.article.id!=null && $scope.article.id!=undefined){
			ArticleManageService.put($scope.article).then(function(data){
				console.log(data);
				if(data.resultCode==1){
					if(data.resultData>0){
						$location.path('/manage/article/preview/'+data.resultData);
					}else{
						alert("修改操作未生效")
					}
				}else{
					alert("操作异常")
				}
				
			})
		}else{
			ArticleManageService.post($scope.article).then(function(data){
				console.log(data);
				if(data.resultCode==1){
					if(data.resultData>0){
						$location.path('/manage/article/preview/'+data.resultData);
					}else{
						alert("添加操作未生效")
					}
				}else{
					alert("操作异常")
				}
			})
		}
		
		
	}
	
	if($routeParams.articleId!=undefined){
		// 获取文章信息
		$scope.get($routeParams.articleId);
	}
	// $scope.getTypes();
	$scope.getCategorys();
	$scope.getTags();
	
});