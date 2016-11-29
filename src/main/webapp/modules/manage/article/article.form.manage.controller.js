app.controller("ArticleFormManageController", function ($scope, $location,$uibModal, $routeParams, ArticleManageService,CategoryManageService) {
    setScreenAvailHeight();
    var articleEditor;

//	console.log($routeParams.articleId)
    $scope.get = function (articleId) {
        ArticleManageService.get(articleId).then(function (data) {
//			console.log(data);
            $scope.article = data.resultData;
            if (data.resultData.tagNames != null) {
                $scope.selectedTagNames = data.resultData.tagNames;
            } else {
                $scope.selectedTagNames = new Array();
            }
        });
    }
    $scope.getTypes = function () {
        ArticleManageService.getTypes().then(function (data) {

        });
    }
    $scope.getCategorys = function () {
        ArticleManageService.getCategorys().then(function (data) {
            $scope.categorys = data.resultData;
        });
    }
    $scope.getTags = function () {
        ArticleManageService.getTags().then(function (data) {
            $scope.tags = data.resultData;
            var s = $scope.selectedTagNames;
            if (s != null) {
                for (var j = 0; j < s.length; j++) {
                    for (var i = 0; i < $scope.tags.length; i++) {
                        if (s[j] == $scope.tags[i].name) {
                            $scope.tags[i].isCheck = true;
                        }
                    }
                }
            }
        });
    }
    $scope.$watch('tagName', function (newValue, oldValue) {
        if (newValue != "" && newValue != undefined) {
            var flagIndex = newValue.indexOf(";");
            flagIndex = flagIndex >= 0 ? flagIndex : newValue.indexOf("；");
            if (flagIndex >= 0) {
                if (flagIndex > 0) {
                    var newName = newValue.substring(0, flagIndex);
                    var existFlag = false;
                    for (var i = 0; i < $scope.selectedTagNames.length; i++) {
                        if ($scope.selectedTagNames[i].toLowerCase() == newName.toLowerCase()) {
                            existFlag = true;
                            break;
                        }
                    }
                    if (!existFlag) {
                        for (var i = 0; i < $scope.tags.length; i++) {
                            if (newName.toLowerCase() == $scope.tags[i].name.toLowerCase()) {
                                $scope.tags[i].isCheck = true;
                            }
                        }
                        $scope.selectedTagNames.push(newName);
                    }
                }
                $scope.tagName = "";
            }
        }

    });
    $scope.deleteTagName = function (tName) {
        var deleteIndex = -1;
        for (var i = 0; i < $scope.selectedTagNames.length; i++) {
            if ($scope.selectedTagNames[i].toLowerCase() == tName.toLowerCase()) {
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex >= 0) {
            $scope.selectedTagNames.splice(deleteIndex, 1);
            for (var i = 0; i < $scope.tags.length; i++) {
                if (tName.toLowerCase() == $scope.tags[i].name.toLowerCase()) {
                    $scope.tags[i].isCheck = false;
                }
            }
        }

    }
    $scope.isSelectExists = false;
    $scope.selectExists = function (status) {
        if ($scope.isSelectExists) {
            $scope.isSelectExists = false;
        } else {
            $scope.getTags();
            $scope.isSelectExists = true;
        }
        if (status == 1) {
            var selectedTags = new Array();
            $(".tagsCheckbox input[type='checkbox']:checked").each(function () {
                for (var i = 0; i < $scope.tags.length; i++) {
                    if (this.title == $scope.tags[i].name) {
                        $scope.tags[i].isCheck = true;
                    }
                }
                selectedTags.push(this.title)
            })

            for (var j = 0; j < selectedTags.length; j++) {
                var existFlag = false;
                for (var i = 0; i < $scope.selectedTagNames.length; i++) {
                    if ($scope.selectedTagNames[i].toLowerCase() == selectedTags[j].toLowerCase()) {
                        existFlag = true;
                        break;
                    }
                }
                if (!existFlag) {
                    $scope.selectedTagNames.push(selectedTags[j]);
                }
            }
            console.log($scope.selectedTagNames);
        }
    }

    $scope.newCategory = function () {
        var modalInstance = $uibModal.open({
            templateUrl: web_project_name+'/modules/manage/category/category.add.view.html',
            controller: 'addCategoryCtrl',
            backdrop: 'static',
            size: 'md',
            resolve: {}
        });
        modalInstance.result.then(function (_category) {
            if (_category != null) {
                CategoryManageService.post(_category).then(function (data) {
                    alert(data.resultMsg)
                    $scope.getCategorys();
                })
            }
        });
    };

    $scope.saveOrUpdate = function (status) {
        $scope.article.status = status;
        $scope.article.tagNames = $scope.selectedTagNames;
        $scope.article.content = articleEditor.getMarkdown();
        //$('#markdown_article_textarea').data('markdown').getContent();//$('#markdown_textarea').data('markdown').parseContent();
        //console.log($scope.article);
        if ($scope.article.id != null && $scope.article.id != undefined) {
            ArticleManageService.put($scope.article).then(function (data) {
                //console.log(data);
                if (data.resultCode == 1) {
                    if (data.resultData > 0) {
                        $location.path('/manage/article/preview/' + data.resultData);
                    } else {
                        alert("修改操作失败,原因如下："+data.resultMsg)
                    }
                } else {
                    alert("操作异常,原因如下："+data.resultMsg)
                }

            })
        } else {
            ArticleManageService.post($scope.article).then(function (data) {
                //console.log(data);
                if (data.resultCode == 1) {
                    if (data.resultData > 0) {
                        $location.path('/manage/article/preview/' + data.resultData);
                    } else {
                        alert("添加操作未生效")
                    }
                } else {
                    alert("操作异常")
                }
            })
        }


    }
    var content;
    if ($routeParams.articleId != undefined) {
        // 获取文章信息
        $scope.get($routeParams.articleId);
    } else {
        $scope.selectedTagNames = new Array();
    }
    // $scope.getTypes();
    $scope.getCategorys();
    $scope.getTags();
    $(function () {
        articleEditor = editormd("article-editormd", {
            width: "100%",
            height: 480,
            watch: false,
            path: web_project_name + "/plugins/editor.md/lib/",
            toolbarIcons: function () {
                return editormd.toolbarModes["simple"];
            },
            onload: function () {
                this.setMarkdown("");
                if ($scope.article != undefined && $scope.article != null) {
                    this.setMarkdown($scope.article.content);
                }
            },
            saveHTMLToTextarea: true,
            autoFocus: false,
            toolbarAutoFixed: false
        });
    });
    $scope.nowIsFirst=true;
    $scope.nowIsLast=false;
    $scope.preStep = function(){
        $scope.nowIsFirst=true;
        $scope.nowIsLast=false;
        $(".article-editor-div").css("display","block");
        $(".article-other-div").css("display","none");
    }
    $scope.nextStep = function(){
        $scope.nowIsFirst=false;
        $scope.nowIsLast=true;
        $(".article-editor-div").css("display","none");
        $(".article-other-div").css("display","block");
    }
});