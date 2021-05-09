<#ftl encoding="UTF-8"/>
<#import  "pager.ftl" as pager>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <style>

        .autocomplete {
            /*the container must be positioned relative:*/
            position: relative;
            display: inline-block;
        }

        .autocomplete-items {
            position: absolute;
            border: 1px solid #d4d4d4;
            border-bottom: none;
            border-top: none;
            z-index: 99;
            /*position the autocomplete items to be the same width as the container:*/
            top: 100%;
            left: 0;
            right: 0;
        }

        .autocomplete-items div {
            color: black;
            padding: 10px;
            cursor: pointer;
            background-color: #fff;
            border-bottom: 1px solid #d4d4d4;
        }

        .autocomplete-items div:hover {
            /*when hovering an item:*/
            background-color: #e9e9e9;
        }

        .autocomplete-active {
            /*when navigating through the items using the arrow keys:*/
            background-color: DodgerBlue !important;
            color: black;
        }
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                    show(JSON.parse(flight.body))
                });
            });
        }

        function show(flight) {
            $("#items").prepend($(' <div class="list-group-item" id="id="' + flight['id'] + '">\n' +
                '                            <div class="row">\n' +
                '                                <div class="col-1">\n' +
                '                                    <p>' + flight['id'] + '</p>\n' +
                '                                </div>\n' +
                '                                <div class="col-3">\n' +
                '                                    <p>' + flight['cityFrom']['city'] + '</p>\n' +
                '                                </div>\n' +
                '                                <div class="col-4">\n' +
                '                                    <p>' + flight['airportFrom'] + '</p>\n' +
                '                                </div>\n' +
                '                                <div class="col-3">\n' +
                '                                    <p>' + flight['dateTimeDep'] + '</p>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <div class="row">\n' +
                '                                <div class="col-1">\n' +
                '                                </div>\n' +
                '                                <div class="col-3">\n' +
                '                                    <p>' + flight['cityTo']['city'] + '</p>\n' +
                '                                </div>\n' +
                '                                <div class="col-3">\n' +
                '                                    <p>' + flight['airportTo'] + '</p>\n' +
                '                                </div>\n' +
                '                                <div class="col-3">\n' +
                '                                    <p>' + flight['dateTimeArr'] + '</p>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <p>\n' +
                '                                <a class="btn btn-primary btn-group-sm" href="/admin/flights/update/' + flight['id'] + '"\n' +
                '                                   role="button">update</a>\n' +
                '                            </p>\n' +
                '                        </div>'
            ));
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        function sendFlight() {
            var data_json = {
                countryTo: document.getElementById('countryTo').value,
                cityTo: document.getElementById('cityTo').value,
                airportTo: document.getElementById('airportTo').value,
                countryFrom: document.getElementById('countryFrom').value,
                cityFrom: document.getElementById("cityFrom").value,
                airportFrom: document.getElementById("airportFrom").value,
                planeType: document.getElementById("planeType").value,
                dateTimeDep: document.getElementById("dateTimeDep").value,
                price: document.getElementById("price").value,
            }
            console.log(data_json)
            stompClient.send("/app/changeFlight", {}, JSON.stringify(data_json));
        }

        window.onload(connect())

        cities = ${cities}
        autocomplete(document.getElementById("cityFrom"), cities)
        autocomplete(document.getElementById("cityTo"), cities)
    </script>

    <div class="col-12" style="min-height: 3em">

    </div>
    <div class="container" onload="connect()">
        <div class="row justify-content-center">
            <div class="col-3">
                <div class="card text-white bg-dark mb-3" style="max-width: 20em;">
                    <div class="card-body">
                        <form autocomplete="off">
                            <div class="form-group">
                                <input id="countryFrom" name="countryFrom" value="Russia" type="text"
                                       placeholder="departure country">
                            </div>
                            <div class="form-group">
                                <div class="autocomplete">
                                    <input id="cityFrom" name="cityFrom" type="text" placeholder="departure city">
                                </div>
                            </div>
                            <div class="form-group">
                                <input id="airportFrom" name="airportFrom" type="text" placeholder="departure airport">
                            </div>
                            <div class="form-group">
                                <input id="countryTo" name="countryTo" value="Russia" type="text"
                                       placeholder="country of arrival">
                            </div>
                            <div class="form-group">
                                <div class="autocomplete">
                                    <input id="cityTo" name="cityTo" type="text" placeholder="city of arrival">
                                </div>
                            </div>
                            <div class="form-group">
                                <input id="airportTo" name="airportTo" type="text" placeholder="airport of arrival">
                            </div>
                            <div class="form-group">
                                <select name="planeType" id="planeType">
                                    <#list planes as plane>
                                        <option value="${plane.id}">${plane.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <input id="dateTimeDep" name="dateDep" type="datetime-local"
                                       placeholder="departure date">
                            </div>
                            <div class="form-group">
                                <input id="price" name="price" type="number" step="0.1" placeholder="price">
                            </div>
                            <button type="button" id="button" onclick="sendFlight()">Add</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-1"></div>
            <div class="col-7">
                <@pager.pager url page/>
                <div class="list-group" id="items">
                    <#list page.content as flight>
                        <div class="list-group-item" id="list-it">
                            <div class="row">
                                <div class="col-1">
                                    <p>${flight.id}</p>
                                </div>
                                <div class="col-3">
                                    <p>${flight.cityFrom.city}</p>
                                </div>
                                <div class="col-4">
                                    <p>${flight.airportFrom}</p>
                                </div>
                                <div class="col-3">
                                    <p>${flight.dateTimeDep}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-1">
                                </div>
                                <div class="col-3">
                                    <p>${flight.cityTo.city}</p>
                                </div>
                                <div class="col-4">
                                    <p>${flight.airportTo}</p>
                                </div>
                                <div class="col-3">
                                    <p>${flight.dateTimeArr}</p>
                                </div>
                            </div>
                            <p>
                                <a class="btn btn-primary btn-group-sm" href="/admin/flights/update/${flight.id}"
                                   role="button">update</a>
                            </p>
                            <p>
                                <a class="btn btn-primary btn-group-sm" href="/admin/flights/delete/${flight.id}"
                                   role="button">delete</a>
                            </p>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">


        cities = ${cities}

        autocomplete(document.getElementById("cityFrom"), cities)
        autocomplete(document.getElementById("cityTo"), cities)

    </script>
</@base.main>