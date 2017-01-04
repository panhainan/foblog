/**
 * Created by Pan on 2016/12/27.
 */
app.controller("ProjectManageController", function ($uibModal,$scope, ProjectManageService) {
    //console.log("ProjectManageController")
    $scope.isProjectNav = true;

    $scope.currentPage = 1;
    $scope.pageSize = 11;
    $scope.pageChanged = function() {
        //console.log('Page changed to: ' + $scope.currentPage);
        $scope.list($scope.currentPage, $scope.pageSize);
    };
    $scope.list = function (currentPage, pageSize) {
        ProjectManageService.list(currentPage, pageSize).then(function (data) {
            //console.log(data)
            $scope.projects = data.resultData.list;
            $scope.totalItems = data.resultData.pageConfig.allCount;
        })
    };
    $scope.list($scope.currentPage, $scope.pageSize);
    $scope.addProject = function () {
        var modalInstance = $uibModal.open({
            templateUrl: 'addProject.html',
            controller: 'addProjectCtrl',
            backdrop: 'static',
            size: 'md',
            resolve: {}
        });
        modalInstance.result.then(function (_project) {
            if (_project != null) {
                ProjectManageService.post(_project).then(function (data) {
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.setEditProject = function (project) {
        var modalInstance = $uibModal.open({
            templateUrl: 'editProject.html',
            controller: 'editProjectCtrl',
            backdrop: 'static',
            size: 'md',
            resolve: {
                project: function () {
                    return project;
                }
            }
        });
        modalInstance.result.then(function (_project) {
            if (_project != null) {
                ProjectManageService.put(_project).then(function (data) {
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
    $scope.previewProject = function (project) {
        var modalInstance = $uibModal.open({
            templateUrl: 'previewProject.html',
            controller: 'previewProjectCtrl',
            backdrop: 'static',
            size: 'md',
            resolve: {
                project: function () {
                    return project;
                }
            }
        });
        modalInstance.result.then(function () {
            //$scope.list($scope.currentPage, $scope.pageSize);
        });
    }
    $scope.setDeleteProject = function (project) {
        var modalInstance = $uibModal.open({
            templateUrl: 'deleteProject.html',
            controller: 'deleteProjectCtrl',
            backdrop: 'static',
            size: 'md',
            resolve: {
                project: function () {
                    return project;
                }
            }
        });
        modalInstance.result.then(function (fId) {
            if (fId != null) {
                ProjectManageService.delete(fId).then(function (data) {
                    $scope.list($scope.currentPage, $scope.pageSize);
                })
            }
        });
    }
});

app.controller("deleteProjectCtrl", function (project, $uibModalInstance, $scope) {
    $scope.project = project;
    $scope.deleteProject = function (fId) {
        $uibModalInstance.close(fId);
    }
    $scope.cancelDeleteProject = function () {
        $uibModalInstance.dismiss('cancel');
    }

});
app.controller("previewProjectCtrl", function (project, $uibModalInstance, $scope) {
    $scope.project = project;
    $scope.closeProject = function () {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("addProjectCtrl", function ($uibModalInstance, $scope) {
    $scope.confirmAddProject = function (project) {
        $uibModalInstance.close(project);
    }
    $scope.cancelAddProject = function () {
        $uibModalInstance.dismiss('cancel');
    }
});
app.controller("editProjectCtrl", function (project, $uibModalInstance, $scope) {
    $scope.project = project;
    $scope.confirmEditProject = function (_project) {
        $uibModalInstance.close(_project);
    }
    $scope.cancelEditProject = function () {
        $uibModalInstance.dismiss('cancel');
    }
});

