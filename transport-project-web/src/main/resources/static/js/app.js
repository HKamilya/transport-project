var stompClient = null;
const handlers = []

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/schedule', flight => {
            handlers.forEach(handler => handler(JSON.parse(flight.cityFrom)))
        });
    });
}

function addHandler(handler) {
    handler.push(handler)
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendFlight(flight) {
    console.log(flight)
    stompClient.send("/app/changeFlight", {}, flight);
}
