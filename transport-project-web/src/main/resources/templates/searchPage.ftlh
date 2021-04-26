<#ftl encoding="UTF-8"/>
<#import "header.ftlh" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $('#button').on("click", function (event) {
                event.preventDefault();
                var cityTo = $('#cityTo').val();
                console.log(cityTo);
                var cityFrom = $("#cityFrom").val();
                var date = $('#date').val();
                var countOfPerson = $('#countOfPerson').val();
                var data_json = {'cityTo': cityTo, 'cityFrom': cityFrom, 'date': date, 'countOfPerson': countOfPerson}
                console.log(JSON.stringify(data_json));
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/search",
                    data: JSON.stringify(data_json),
                    contentType: "application/json; charset = utf-8",
                    success: function (result) {
                        $('#flights').empty()
                        for (var i = 0; i < result.length; i++) {
                            var ids = ""
                            $("#flights").append('<div class="jumbotron" id="flights2"></div>')
                            for (var j = 0; j < result[i].length; j++) {
                                console.log(result[i][j]['dateTimeDep'])
                                ids += " " + result[i][j]['id']
                                $("#flights2").append($('<div class="row" id="flights3">\n' +
                                    '            <div class="col-2">\n' +
                                    '                <p class="lead" id="cityFrom">' + result[i][j]['cityFrom']['city'] + '</p>\n' +
                                    '               <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">\n' +
                                    '  <path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"/>\n' +
                                    '</svg>\n' +
                                    '                <p class="lead" id="cityTo">' + result[i][j]['cityTo']['city'] + '</p>\n' +
                                    '            </div>\n' +
                                    '            <div class="col-2">\n' +
                                    '                <p class="lead" id="dateDep">' + result[i][j]['dateTimeDep'] + '</p>\n' +
                                    '                <p class="lead" id="dateArr">' + result[i][j]['dateTimeArr'] + '</p>\n' +
                                    '            </div>\n' +
                                    '            <div class="col-1">\n' +
                                    '                <p class="lead" id="price">' + result[i][j]['price'] + '</p>\n' +
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
    <div class="container">
        <div class="row">
            <div class="col-1">

            </div>
            <form>
                <div class="row">
                    <div class="form-group">
                        <label for="cityFrom">City from</label>
                        <input type="text" name="cityFrom" class="form-control" id="cityFrom"
                               placeholder="city from">
                    </div>
                    <div class="form-group">
                        <label for="cityTo">City to</label>
                        <input type="text" name="cityTo" class="form-control" id="cityTo"
                               placeholder="city to">
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
            </form>
        </div>
        <div id="flights">
            <#--        <div class="jumbotron">-->
            <#--            <div class="row">-->
            <#--                <div class="col-2">-->
            <#--                    <p class="lead" id="cityFrom"></p>-->
            <#--                    <p class="lead" id="cityTo"></p>-->
            <#--                </div>-->
            <#--                <div class="col-2">-->
            <#--                    <p class="lead" id="dateDep"></p>-->
            <#--                    <p class="lead" id="dateArr"></p>-->
            <#--                </div>-->
            <#--                <div class="col-1">-->
            <#--                    <p class="lead" id="price"></p>-->
            <#--                </div>-->
            <#--                -->
            <#--            </div>-->

            <#--        </div>-->
        </div>

    </div>
</@base.main>