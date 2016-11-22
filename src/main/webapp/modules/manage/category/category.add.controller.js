/**
 * Created by Pan on 2016/11/22.
 */
app.controller("addCategoryCtrl",function($uibModalInstance,$scope){
    $scope.confirmAddCategory = function(category){
        $uibModalInstance.close(category);
    };
    $scope.cancelAddCategory= function() {
        $uibModalInstance.dismiss('cancel');
    }
})