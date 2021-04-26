<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <div class="container" onload="priceFunc()">
        <div class="justify-content-center row">
            <div class="col-8">
                <div class="jumbotron">
                    <form method="post" action="/admin/flights/update/${flight.id}">
                        <div class="col-4">
                            <div class="form-group">
                                <label class="col-form-label" for="countryFrom">Country from:</label>
                                <input type="text" class="form-control" value="${flight.cityFrom.country}"
                                       name="countryFrom">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="cityFrom">City from:</label>
                                <input type="text" class="form-control" value="${flight.cityFrom.city}" name="cityFrom">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="airportFrom">Airport from:</label>
                                <input type="text" class="form-control" value="${flight.airportFrom}"
                                       name="airportFrom">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="planeType">Plane type:</label>
                                <input type="text" class="form-control" value="${flight.planeType}" name="planeType">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="countOfPlaces">Count of passengers:</label>
                                <input type="number" class="form-control" value="${flight.countOfPlaces}"
                                       name="countOfPlaces">
                            </div>
                        </div>

                        <div class="col-4">
                            <div class="form-group">
                                <label class="col-form-label" for="countryTo">Country to:</label>
                                <input type="text" class="form-control" value="${flight.cityTo.country}"
                                       name="countryTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="cityTo">City to:</label>
                                <input type="text" class="form-control" value="${flight.cityTo.city}" name="cityTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="airportTo">Airport to:</label>
                                <input type="text" class="form-control" value="${flight.airportTo}" name="airportTo">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="dateTimeDep">Departure date:</label>
                                <input type="text" class="form-control" value="${flight.dateTimeDep}"
                                       name="dateTimeDep">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label" for="price">Price:</label>
                                <input type="text" class="form-control" value="${price}" name="price" id="priceInput">
                            </div>
                            <div class="form-group">
                                <select class="custom-select" name="state">
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="CANCELED">CANCELED</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-outline-success">update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@base.main>