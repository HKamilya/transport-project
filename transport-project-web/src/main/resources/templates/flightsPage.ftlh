<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>flights</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
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
                    <form method="post" action="/admin/addFlight">
                        <input name="countryFrom" type="text" placeholder="departure country">
                        <input name="cityFrom" type="text" placeholder="departure city">
                        <input name="airportFrom" type="text" placeholder="departure airport">
                        <input name="countryTo" type="text" placeholder="country of arrival">
                        <input name="cityTo" type="text" placeholder="city of arrival">
                        <input name="airportTo" type="text" placeholder="airport of arrival">
                        <input name="planeType" type="text" placeholder="airplane type">
                        <input name="dateDep" type="date" placeholder="departure date">
                        <input name="timeDep" type="time" placeholder="departure time">
                        <input name="dateArr" type="date" placeholder="date of arrival">
                        <input name="timeArr" type="time" placeholder="time of arrival">
                        <input name="countOfPlaces" type="number" placeholder="count of passengers">
                        <input name="price" type="number" step="0.1" placeholder="price">
                        <button type="submit">Ок</button>
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