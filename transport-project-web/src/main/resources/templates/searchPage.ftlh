<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>search</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
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
                    url: window.location.href,
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
<div class="container">
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
</body>
</html>