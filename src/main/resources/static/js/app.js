angular.module('main', []).controller("mainController", ['$scope', '$http', MainController]);

function MainController($scope, $http) {
    $http.get("/rest").then(function(data){
        $scope.data = data;
    });
}
