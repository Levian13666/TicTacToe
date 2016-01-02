angular.module('main', []).controller('mainController', ['$scope', '$http', 'socketService', '$log', MainController]);

function MainController($scope, $http, socketService, $log) {

    $scope.endGame = false;

    var size = 200;
    var cellSize = size * (1 / 3);

    var svg = d3.select('svg').attr('width', size).attr('height', size);

    var grid = [{
        x1: size / 3,
        y1: 0,
        x2: size / 3,
        y2: size
    }, {
        x1: size * (2 / 3),
        y1: 0,
        x2: size * (2 / 3),
        y2: size
    }, {
        x1: 0,
        y1: size / 3,
        x2: size,
        y2: size / 3
    }, {
        x1: 0,
        y1: size * (2 / 3),
        x2: size,
        y2: size * (2 / 3)
    }];

    var cell = [
        {x:0,y:0},{x:0,y:1},{x:0,y:2},
        {x:1,y:0},{x:1,y:1},{x:1,y:2},
        {x:2,y:0},{x:2,y:1},{x:2,y:2}
    ];

    svg.selectAll('rect').data(cell).enter().append('rect')
        .attr('class', 'cell')
        .attr('x', function(d) {
            return d.x * cellSize;
        })
        .attr('y', function(d) {
            return d.y * cellSize;
        })
        .attr('width', function(d) {
            return cellSize;
        })
        .attr('height', function(d) {
            return cellSize;
        })
        .on('click', function(d) {
            drawStep('x', d.x, d.y);
            $http.get('/rest/step', {params: {x: d.y, y: d.x}}).then(function(res){
                var result = res.data.result;
                $log.log(result);
                if (result.step.length > 0) {
                    drawStep('o', result.step[1], result.step[0]);
                }
                if (result.winner) {
                    $scope.endGame = true;
                    drawWinnerLine(result.winCoordinates);
                }
            });
        });

    svg.selectAll('line').data(grid).enter().append('line')
        .attr('class', 'grid')
        .attr('x1', function(d) {
            return d.x1;
        })
        .attr('y1', function(d) {
            return d.y1;
        })
        .attr('x2', function(d) {
            return d.x2;
        })
        .attr('y2', function(d) {
            return d.y2;
        });

    function drawWinnerLine(d) {
        svg.append('line')
            .attr('class', 'gameObject')
            .attr('x1', d[1] * cellSize + cellSize / 2)
            .attr('y1', d[0] * cellSize + cellSize / 2)
            .attr('x2', d[3] * cellSize + cellSize / 2)
            .attr('y2', d[2] * cellSize + cellSize / 2)
            .attr('font-size', cellSize / 2 + 'px')
            .style('stroke-width', '5px')
            .style('stroke', 'red');
    }

    function drawStep(type, x, y) {
        if (type == 'x') {
            svg.append('line')
                .attr('class', 'gameObject')
                .attr('x1', x * cellSize + 10)
                .attr('y1', y * cellSize + 10)
                .attr('x2', x * cellSize + cellSize - 10)
                .attr('y2', y * cellSize + cellSize - 10)
                .attr('font-size', cellSize / 2 + 'px')
                .style('stroke-width', '5px')
                .style('stroke', 'blue');
            svg.append('line')
                .attr('class', 'gameObject')
                .attr('x1', x * cellSize + 10)
                .attr('y1', y * cellSize + cellSize - 10)
                .attr('x2', x * cellSize + cellSize - 10)
                .attr('y2', y * cellSize + 10)
                .attr('font-size', cellSize / 2 + 'px')
                .style('stroke-width', '5px')
                .style('stroke', 'blue');
        } else {
            svg.append('circle')
                .attr('class', 'gameObject')
                .attr('cx', x * cellSize + (cellSize / 2))
                .attr('cy', y * cellSize + (cellSize / 2))
                .attr('r', cellSize / 3)
                .style('fill', 'none')
                .style('stroke-width', '5px')
                .style('stroke', 'green');
        }
    }

    $scope.reset =  function() {
        svg.selectAll('.gameObject').remove();
        $scope.endGame = false;
        $http.get('/rest/reset');
    };

    $scope.letStart =  function() {
        $http.get('/rest/letStart').then(function(res){
            var result = res.data.result;
            $log.log(result);
            if (result.step.length > 0) {
                drawStep('o', result.step[1], result.step[0]);
            }
        });
    };

    /*$http.get('/rest').then(function(res){
        $scope.data = 'Rest: ' + res.data.result;
    });

    $scope.connect = function() {
        $scope.socketMessages = [];
        socketService.connect(function(response){
            $scope.socketMessages.unshift(response.body.replace(/'/g,''));
            $scope.$apply();
        });
    };

    $scope.disconnect = function() {
        socketService.disconnect();
    }*/
}
