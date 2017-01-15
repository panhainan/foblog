app.controller("SignManageController", function ($scope, $rootScope, SignManageService, $location) {
    $scope.signout = function () {
        //console.log("退出")
        SignManageService.delete().then(function (data) {
            sessionStorage.removeItem("token");
            $location.path("/manage/sign");
        });
    }
    var doSignin = function (author) {
        SignManageService.post(author).then(function (data) {
            //console.log(data);
            if (data.resultCode == 1) {
                sessionStorage.setItem("token", data.resultData);
                $location.path("/manage/info");
            } else {
                $scope.user.account = null;
                $scope.user.password = null;
                $scope.user.captcha = null;
                $scope.refreshCode();
                alert(data.resultMsg);
            }
        })
    }
    $scope.refreshCode = function () {
        if ($scope.logining) {
            return false;
        }
        $("#imgValidateCode").attr('src', '/manage/getCaptcha?temp=' + Math.random());
    };
    $scope.signin = function () {
        $.ajax({
            type: 'GET',
            url: web_project_name + '/manage/getKeys',
            dataType: "json",
            success: function (data) {
                //console.log(data)
                if (data.resultCode == 0) {
                    alert(data.resultMsg)
                    return false;
                } else {
                    var keys = genkeys(data.resultData.e, data.resultData.n, data.resultData.maxdigits);
                    //console.log(keys)
                    var author = {
                        account: "",
                        password: "",
                        captcha:$scope.user.captcha
                    }
                    if (keys == null || keys == "") {
                        // 没有加密串，不能登录，需要先获取加密串
                        alert("服务器异常，生成密钥失败！");
                        return false;
                    } else {
                        $.jCryption.encrypt(encodeURIComponent($scope.user.account), keys,
                            function (encryptedAccount) {
                                author.account = encryptedAccount;
                                $.jCryption.encrypt(
                                    encodeURIComponent($scope.user.password), keys,
                                    function (encryptedPassword) {
                                        author.password = encryptedPassword;
//											console.log(author);
                                        doSignin(author);
                                    });
                            });
                    }
                }
            }

        });
        //$.jCryption.getKeys(web_project_name + '/manage/getKeys1', function(data) {
        //    var keys=data;
        //    console.log(keys);
        //});
    }


})