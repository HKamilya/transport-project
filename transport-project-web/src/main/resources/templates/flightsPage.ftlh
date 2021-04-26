<#ftl encoding="UTF-8"/>
<#import  "pager.ftlh" as pager>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $('#button').on("click", function (event) {
                event.preventDefault();
                var countryTo = $('#countryTo').val();
                var cityTo = $('#cityTo').val();
                var airportTo = $('#airportTo').val();
                var countryFrom = $('#countryFrom').val();
                var cityFrom = $("#cityFrom").val();
                var airportFrom = $("#airportFrom").val();
                var planeType = $("#planeType").val();
                var dateTimeDep = $("#dateTimeDep").val();
                var countOfPlaces = $("#countOfPlaces").val();
                var price = $("#price").val();
                var distance = $("#distance").val();
                var data_json = {
                    'countryTo': countryTo,
                    'cityTo': cityTo,
                    'airportTo': airportTo,
                    'countryFrom': countryFrom,
                    'cityFrom': cityFrom,
                    'airportFrom': airportFrom,
                    'planeType': planeType,
                    'dateTimeDep': dateTimeDep,
                    'countOfPlaces': countOfPlaces,
                    'price': price
                }
                console.log(JSON.stringify(data_json));
                $.ajax({
                    type: "POST",
                    url: "/admin/flights",
                    data: JSON.stringify(data_json),
                    contentType: "application/json; charset = utf-8",
                    success: function (result) {
                    }
                });
            });
        });
    </script>

    <div class="col-12" style="min-height: 3em">

    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-3">
                <div class="card text-white bg-dark mb-3" style="max-width: 20em;">
                    <div class="card-body">
                        <form>
                            <input id="countryFrom" name="countryFrom" type="text" placeholder="departure country">
                            <input id="cityFrom" oname="cityFrom" type="text" placeholder="departure city">
                            <input id="airportFrom" name="airportFrom" type="text" placeholder="departure airport">
                            <input id="countryTo" name="countryTo" type="text" placeholder="country of arrival">
                            <input id="cityTo" name="cityTo" type="text" placeholder="city of arrival">
                            <input id="airportTo" name="airportTo" type="text" placeholder="airport of arrival">
                            <input id="planeType" name="planeType" type="text" placeholder="airplane type">
                            <input id="dateTimeDep" name="dateDep" type="datetime-local" placeholder="departure date">
                            <input id="countOfPlaces" name="countOfPlaces" type="number"
                                   placeholder="count of passengers">
                            <input id="price" name="price" type="number" step="0.1" placeholder="price">
                            <button type="button" id="button">Add</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-1"></div>
            <div class="col-7">
                <@pager.pager url page/>
                <div class="list-group">
                    <#list page.content as flight>
                        <div class="list-group-item">
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
                                <div class="col-3">
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
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
    <script>
        function autocomplete(inp, arr) {
            var currentFocus;
            inp.addEventListener("input", function (e) {
                var a, b, i, val = this.value;
                closeAllLists();
                if (!val) {
                    return false;
                }
                currentFocus = -1;
                a = document.createElement("DIV");
                a.setAttribute("id", this.id + "autocomplete-list");
                a.setAttribute("class", "autocomplete-items");
                this.parentNode.appendChild(a);
                for (i = 0; i < arr.length; i++) {
                    if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                        /*create a DIV element for each matching element:*/
                        b = document.createElement("DIV");
                        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                        b.innerHTML += arr[i].substr(val.length);
                        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                        b.addEventListener("click", function (e) {
                            inp.value = this.getElementsByTagName("input")[0].value;
                            closeAllLists();
                        });
                        a.appendChild(b);
                    }
                }
            });
            inp.addEventListener("keydown", function (e) {
                var x = document.getElementById(this.id + "autocomplete-list");
                if (x) x = x.getElementsByTagName("div");
                if (e.keyCode == 40) {
                    currentFocus++;
                    addActive(x);
                } else if (e.keyCode == 38) {
                    addActive(x);
                } else if (e.keyCode == 13) {

                    e.preventDefault();
                    if (currentFocus > -1) {

                        if (x) x[currentFocus].click();
                    }
                }
            });

            function addActive(x) {
                if (!x) return false;
                removeActive(x);
                if (currentFocus >= x.length) currentFocus = 0;
                if (currentFocus < 0) currentFocus = (x.length - 1);
                x[currentFocus].classList.add("autocomplete-active");
            }

            function removeActive(x) {
                for (var i = 0; i < x.length; i++) {
                    x[i].classList.remove("autocomplete-active");
                }
            }

            function closeAllLists(elmnt) {
                var x = document.getElementsByClassName("autocomplete-items");
                for (var i = 0; i < x.length; i++) {
                    if (elmnt != x[i] && elmnt != inp) {
                        x[i].parentNode.removeChild(x[i]);
                    }
                }
            }

            document.addEventListener("click", function (e) {
                closeAllLists(e.target);
            });
        }

        var names = 0;
        console.log(names)
        autocomplete(document.getElementById("cityFrom"), names);
        autocomplete(document.getElementById("cityTo"), names);
    </script>
</@base.main>