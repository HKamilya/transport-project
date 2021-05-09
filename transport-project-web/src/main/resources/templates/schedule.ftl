<#ftl encoding="UTF-8"/>
<#import  "pager.ftl" as pager>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            var id = flight['id']
            content = $(id).detach();

            $("#items").prepend($(' <div class="list-group-item" id="' + flight['id'] + '">\n' +
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
                '                        </div>'
            ))
            ;
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        window.onload(connect())
    </script>

    <div class="container">
        <@pager.pager url page/>
        <div class="list-group" id="items">
            <#list page.content as flight>
                <div class="list-group-item" id="${flight.id}">
                    <div class="row">
                        <div class="col-1">
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
                        <div class="col-1">

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
                        <div class="col-1">
                            <p>${flight.state}</p>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@base.main>