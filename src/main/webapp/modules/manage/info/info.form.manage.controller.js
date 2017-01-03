app.controller("InfoFormManageController", function($location,$scope,InfoManageService) {
	$scope.isInfoNav = true;
	setScreenAvailHeight();
    var infoEditor;
	var getInfo = function(){
		InfoManageService.get().then(function(data){
			//console.log(data);
			if(data.resultData!=null){
				$scope.info = data.resultData;
			}
		})
	}
	$scope.updateInfo = function(info){
		info.profile = infoEditor.getMarkdown();
		InfoManageService.put(info).then(function(data){
			//console.log(data)
			if(data.resultCode==1){
				$location.path("/manage/info");
			}
		})
	}
	getInfo();
    $(function () {
        infoEditor = editormd("info-editormd", {
            width: "100%",
            height: 520,
            watch: false,
            path: web_project_name + "/plugins/editor.md/lib/",
            toolbarIcons: function () {
                return editormd.toolbarModes["simple"];
            },
            onload: function () {
                this.setMarkdown("");
                if ($scope.info != undefined && $scope.info != null) {
                    this.setMarkdown($scope.info.profile);
                }
            },
            saveHTMLToTextarea: true,
            autoFocus: false,
            toolbarAutoFixed: false
        });
    });
});