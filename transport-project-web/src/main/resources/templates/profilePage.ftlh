<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <div class="container">
        <div class="row">
            <div class="col-2">
                <img src="user.png" style="width: 100%">
            </div>
            <div class="col-2">
                ${username}!
            </div>
        </div>
        <div class="col-12" style="min-height: 3em">
        </div>
        <div class="row">
            <div class="col">
                <h6>New flights</h6>
                <div class="jumbotron" id="flights2">
                    <#list comingFlights as flight1>
                        <div class="row" id="flights3">
                            <div class="col-5">
                                <h6 id="cityFrom">${flight1.flight.cityFrom.city}</h6>
                                <h7 id="airportFrom">${flight1.flight.airportFrom}</h7>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-arrow-down"
                                     viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
                                </svg>
                                <h6 id="cityTo">${flight1.flight.cityTo.city}</h6>
                                <h7 id="airportFrom">${flight1.flight.airportTo}</h7>
                            </div>
                            <div class="col-3">
                                <h6 id="dateDep">${flight1.flight.dateTimeDep}</h6>
                                <h6 id="dateArr">${flight1.flight.dateTimeArr}</h6>
                            </div>
                            <div class="col-2">
                                <h6 id="price">${flight1.flight.price}</h6>
                            </div>
                            <div class="col">
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="col">
                <h6>Last flights</h6>
                <div class="jumbotron" id="flights2">
                    <#list comingFlights as flight1>
                        <div class="row" id="flights3">
                            <div class="col-5">
                                <h6 id="cityFrom">${flight1.flight.cityFrom.city}</h6>
                                <h7 id="airportFrom">${flight1.flight.airportFrom}</h7>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-arrow-down"
                                     viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>
                                </svg>
                                <h6 id="cityTo">${flight1.flight.cityTo.city}</h6>
                                <h7 id="airportFrom">${flight1.flight.airportTo}</h7>
                            </div>
                            <div class="col-3">
                                <h6 id="dateDep">${flight1.flight.dateTimeDep}</h6>
                                <h6 id="dateArr">${flight1.flight.dateTimeArr}</h6>
                            </div>
                            <div class="col-2">
                                <h6 id="price">${flight1.flight.price}</h6>
                            </div>
                            <div class="col">
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</@base.main>