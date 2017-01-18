/**
 * Created by Pan on 2016/9/11.
 */

app.controller("FriendlinkController", function (FriendlinkService, $scope) {
    setScreenAvailHeight();
    $scope.loadingPath=loading_path;
    $scope.loaded = false;

    $scope.clickLink = function(id,url){
        FriendlinkService.hits(id).then(function(data){
//			console.log(data)
        });
        window.open(url);
    }
    $scope.list = function(){
        FriendlinkService.list().then(function(data){
            $scope.friendlinks = data.resultData;
            $scope.loaded = true;
        })
    };
    $scope.list();
});