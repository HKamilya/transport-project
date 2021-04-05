<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>flights</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $('#button').on("click", function (event) {
                event.preventDefault();
                // private String countryTo;
                // private String cityTo;
                // private String airportTo;
                // private String countryFrom;
                // private String cityFrom;
                // private String airportFrom;
                // private String planeType;
                // private String dateDep;
                // private String dateArr;
                // private String timeDep;
                // private String timeArr;
                // private int countOfPlaces;
                // private float price;
                var countryTo = $('#countryTo').val();
                var cityTo = $('#cityTo').val();
                var airportTo = $('#airportTo').val();
                var countryFrom = $('#countryFrom').val();
                var cityFrom = $("#cityFrom").val();
                var airportFrom = $("#airportFrom").val();
                var planeType = $("#planeType").val();
                var dateDep = $("#dateDep").val();
                var timeDep = $("#timeDep").val();
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
                    'dateDep': dateDep,
                    'distance': distance,
                    'timeDep': timeDep,
                    'countOfPlaces': countOfPlaces,
                    'price': price
                }
                console.log(JSON.stringify(data_json));
                $.ajax({
                    type: "POST",
                    url: "/flights",
                    data: JSON.stringify(data_json),
                    contentType: "application/json; charset = utf-8",
                    success: function (result) {
                    }
                });
            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">ALPHA FLIGHT</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02"
            aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/signIn">SignIn</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signUp">SignUp</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/search">Search</a>
            </li>
        </ul>
    </div>
</nav>
<div class="col-12" style="min-height: 3em">

</div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-3">
            <div class="card text-white bg-dark mb-3" style="max-width: 20em;">
                <div class="card-body">
                    <form>
                        <input id="countryFrom" name="countryFrom" type="text" placeholder="departure country">
                        <input id="cityFrom" name="cityFrom" type="text" placeholder="departure city">
                        <input id="airportFrom" name="airportFrom" type="text" placeholder="departure airport">
                        <input id="countryTo" name="countryTo" type="text" placeholder="country of arrival">
                        <input id="cityTo" name="cityTo" type="text" placeholder="city of arrival">
                        <input id="airportTo" name="airportTo" type="text" placeholder="airport of arrival">
                        <input id="planeType" name="planeType" type="text" placeholder="airplane type">
                        <input id="dateDep" name="dateDep" type="date" placeholder="departure date">
                        <input id="timeDep" name="timeDep" type="time" placeholder="departure time">
                        <input id="distance" name="distance" type="text" step="0.1" placeholder="distance">
                        <input id="countOfPlaces" name="countOfPlaces" type="number" placeholder="count of passengers">
                        <input id="price" name="price" type="number" step="0.1" placeholder="price">
                        <button type="button" id="button">Add</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-9">

        </div>
    </div>
</div>

</body>
</html>