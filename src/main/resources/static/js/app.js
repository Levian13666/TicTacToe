angular.module('main', []).controller('mainController', ['$scope', '$http', 'socketService', '$log', MainController]);

function MainController($scope, $http, socketService, $log) {
    $http.get('/rest').then(function(res){
        $scope.data = 'Rest: ' + res.data.result;
    });

    $scope.connect = function() {
        $scope.socketMessages = [];
        socketService.connect(function(response){
            $scope.socketMessages.unshift(response.body.replace(/"/g,''));
            $scope.$apply();
        });
    };

    $scope.disconnect = function() {
        socketService.disconnect();
    }
}
