<#ftl encoding="UTF-8"/>
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
            color: #ffffff;
        }
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/js/autocomplete.js"></script>
    <script>
        var cities = []

        function getCities() {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/api/cities",
                contentType: "application/json; charset = utf-8",
                success: function (result) {
                    cities = result
                    console.log(cities)
                }
            })
        }

    </script>
    <script>

        $(function () {
            $('#button').on("click", function (event) {
                event.preventDefault();
                var cityTo = $('#cityTo').val();
                console.log(cityTo);
                var cityFrom = $("#cityFrom").val();
                var date = $('#date').val();
                var countOfPerson = $('#countOfPerson').val();
                var sort = $('#sort').val();
                var data_json = {
                    'cityTo': cityTo,
                    'cityFrom': cityFrom,
                    'date': date,
                    'countOfPerson': countOfPerson,
                    'sort': sort,
                }
                console.log(JSON.stringify(data_json));
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/search",
                    data: JSON.stringify(data_json),
                    contentType: "application/json; charset = utf-8",
                    success: function (result) {
                        console.log(result)
                        $('#flights').empty()
                        for (var i = 0; i < result.length; i++) {
                            var ids = ""
                            $("#flights").append('<div  id="flights2"></div>')
                            for (var j = 0; j < result[i]['flights'].length; j++) {
                                console.log(result[i]['flights'][j]['dateTimeDep'])
                                ids += " " + result[i]['flights'][j]['id']
                                $("#flights2").append($('<div class="jumbotron row"  id="flights3">\n' +
                                    '            <div class="col-2">\n' +
                                    '                <p class="lead" id="cityFrom">' + result[i]['flights'][j]['cityFrom']['city'] + '</p>\n' +
                                    '               <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">\n' +
                                    '  <path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>\n' +
                                    '</svg>\n' +
                                    '                <p class="lead" id="cityTo">' + result[i]['flights'][j]['cityTo']['city'] + '</p>\n' +
                                    '            </div>\n' +
                                    '            <div class="col-2">\n' +
                                    '                <p class="lead" id="dateDep">' + result[i]['flights'][j]['dateTimeDep'] + '</p>\n' +
                                    '                <p class="lead" id="dateArr">' + result[i]['flights'][j]['dateTimeArr'] + '</p>\n' +
                                    '            </div>\n' +
                                    '            <div class="col-1">\n' +
                                    '                <p class="lead" id="price">' + result[i]['flights'][j]['price'] + '</p>\n' +
                                    '            </div>' +
                                    '<div class="col">\n' +
                                    '                </div>'
                                ))
                            }
                            $("#flights2").append($('<div class="row">\n' +
                                '                <div class="col">\n' +
                                '                </div>\n' +
                                '                <div class="col">\n' +
                                '                </div>\n' +
                                '                <div class="col">\n' +
                                '                </div>\n' +
                                '                <div class="col">\n' +
                                '                  <form method="post" action="/reserve"> ' +
                                '<input type="hidden" name="ids" value="' + ids + '">\n' +
                                '<input type="hidden" name="count" value="' + countOfPerson + '">\n' +
                                '                   <button type="submit" id="button2" class="btn btn-lg btn-success">get it</button>\n' +
                                '                   </form>' +
                                '                   </div>' +
                                '               </div>'
                            ))


                        }
                    }
                });
            });
        });

    </script>
    <div class="col-12" style="min-height: 3em">

    </div>
    <div class="container">
        <div class="row">
            <div class="col-1">

            </div>
            <form autocomplete="off">
                <div class="row">
                    <div class="form-group">
                        <div class="autocomplete">
                            <label for="cityFrom">City from</label>
                            <input type="text" name="cityFrom" class="form-control"
                                   id="cityFrom"
                                   placeholder="city from">
                        </div>
                    </div>
                    <div id="match-list-from">

                    </div>
                    <div class="form-group">
                        <div class="autocomplete">
                            <label for="cityTo">City to</label>
                            <input type="text" name="cityTo" class="form-control" id="cityTo"
                                   placeholder="city to">
                        </div>
                    </div>
                    <div id="match-list-to">

                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="date" name="date" class="form-control" id="date"
                               placeholder="Date">
                    </div>
                    <div class="form-group">
                        <label for="countOfPerson">Count of passengers</label>
                        <input type="number" name="countOfPerson" class="form-control" id="countOfPerson"
                               placeholder="count of passengers">
                    </div>
                    <div class="form-group">
                        <br>
                        <label for="countOfPerson"></label>
                        <button type="button" id="button" class="btn btn-lg btn-success">Search</button>
                    </div>
                </div>
                <div class="col-9"></div>
                <div class="col-2">
                    <div class="form-group">
                        <select class="custom-select" name="sort" id="sort">
                            <option value="1">by distance</option>
                            <option value="2">by price</option>
                            <option value="3">by time</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <div id="flights">
        </div>

    </div>

    <script type="text/javascript">


        cities = ${cities}

        autocomplete(document.getElementById("cityFrom"), cities)
        autocomplete(document.getElementById("cityTo"), cities)

    </script>
</@base.main>