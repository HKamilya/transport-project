<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <script src="/js/autocomplete.js"></script>
    <script>
        var stompClient = null;
        const handlers = []

        function connect() {
            const socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, frame => {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/schedule', flight => {

                });
            });
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        function sendFlight() {
            var data_json = {
                id: document.getElementById('id').value,
                countryTo: document.getElementById('countryTo').value,
                cityTo: document.getElementById('cityTo').value,
                airportTo: document.getElementById('airportTo').value,
                countryFrom: document.getElementById('countryFrom').value,
                cityFrom: document.getElementById("cityFrom").value,
                airportFrom: document.getElementById("airportFrom").value,
                planeType: document.getElementById("planeType").value,
                dateTimeDep: document.getElementById("dateTimeDep").value,
                countOfPlaces: document.getElementById("countOfPlaces").value,
                price: document.getElementById("price").value,
                state: document.getElementById("state").value
            }
            console.log(data_json)
            stompClient.send("/app/changeFlight", {}, JSON.stringify(data_json));
        }

        window.onload(connect())

        cities = ${cities}
        autocomplete(document.getElementById("cityFrom"), cities)
        autocomplete(document.getElementById("cityTo"), cities)
    </script>
    <div class="container">
        <div class="justify-content-center row">
            <div class="col-8">
                <div class="jumbotron">
                    <form method="post" action="/admin/flights/update/${flight.id}">
                        <div class="col-4">
                            <input type="hidden" name="id" id="id" value="${flight.id}">
                            <div class="form-group">
                                <label class="col-form-label" for="countryFrom">Country from:</label>
                                <input type="text" class="form-control" id="countryFrom"
                                       value="${flight.cityFrom.country}"
                                       name="countryFrom">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="cityFrom">City from:</label>
                                <input type="text" id="cityFrom" class="form-control" value="${flight.cityFrom.city}"
                                       name="cityFrom">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="airportFrom">Airport from:</label>
                                <input type="text" class="form-control" id="airportFrom" value="${flight.airportFrom}"
                                       name="airportFrom">
                            </div>
                            <div class="form-group">
                                <select class="custom-select" name="planeType" id="planeType">
                                    <option selected value="${flight.planeType.id}">${flight.planeType.name}</option>
                                    <#list planes as plane>
                                        <option value="${plane.id}">${plane.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="countOfPlaces">Count of passengers:</label>
                                <input type="number" class="form-control" id="countOfPlaces"
                                       value="${flight.countOfPlaces}"
                                       name="countOfPlaces">
                            </div>
                        </div>

                        <div class="col-4">
                            <div class="form-group">
                                <label class="col-form-label" for="countryTo">Country to:</label>
                                <input type="text" class="form-control" id="countryTo" value="${flight.cityTo.country}"
                                       name="countryTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="cityTo">City to:</label>
                                <input type="text" id="cityTo" class="form-control"
                                       value="${flight.cityTo.city}"
                                       name="cityTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="airportTo">Airport to:</label>
                                <input type="text" class="form-control" id="airportTo" value="${flight.airportTo}"
                                       name="airportTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="dateTimeDep">Departure date:</label>
                                <input type="text" class="form-control" id="dateTimeDep" value="${flight.dateTimeDep}"
                                       name="dateTimeDep">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="price">Price:</label>
                                <input type="text" class="form-control" value="${price}" name="price"
                                       id="price">
                            </div>
                            <div class="form-group">
                                <select class="custom-select" name="state" id="state">
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="CANCELED">CANCELED</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-outline-success" onclick="sendFlight()">update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@base.main>