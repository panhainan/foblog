app.controller("ProjectController",function(ProjectService,$scope){
    setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;

    $scope.hit=function(url,id){
        ProjectService.hits(id).then(function(data){
//			console.log(data)
        });
        window.open(url);
    }
    $scope.list = function(){
        ProjectService.list().then(function(data){
//			console.log(data)
            $scope.projects = data.resultData;
            $scope.loaded = true;
        });
    }

    $scope.list();
})