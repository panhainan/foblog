app.controller("leftNavController",function($scope){
    $scope.blogNavStatus = true;//close
    $scope.clickBlogNav = function(){
        //console.log("博客菜单")
        if($scope.blogNavStatus){
            $scope.blogNavStatus = false;
            $("#blog-ul").collapse('show')//.css("display","block")
        }else{
            $scope.blogNavStatus = true;
            $("#blog-ul").collapse('hide')//.css("display","none")
        }

    }
})