angular.module('main').service('socketService', ['$log', SocketService]);

function SocketService($log) {
    var stompClient = null;

    return {
        connect: connect,
        disconnect: disconnect
    };

    function connect(subscription) {
        var socket = new SockJS('/connect');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            $log.log('Connected: ' + frame);
            stompClient.subscribe('/socket/answer', subscription);
            stompClient.send("/app/connect");
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        } else {
            $log.warn("WARN: Stomp Client is NULL")
        }
        $log.log("Disconnected");
    }

}