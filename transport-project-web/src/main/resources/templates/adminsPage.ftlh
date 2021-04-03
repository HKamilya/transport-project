<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>admins</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $('#button').on("click", function (event) {
                event.preventDefault();
                var username = $("#username").val()
                var email = $("#email").val()
                var firstname = $("#firstname").val()
                var lastname = $("#lastname").val()
                var password = $("#password").val()
                var phoneNumber = $("#phoneNumber").val()
                var dateOfBirth = $("#dateOfBirth").val()
                var confPass = $("#confpassword").val()
                if (password === confPass) {
                    var data_json = {
                        'username': username,
                        'email': email,
                        'firstname': firstname,
                        'lastname': lastname,
                        'password': password,
                        'phoneNumber': phoneNumber,
                        'dateOfBirth': dateOfBirth
                    }
                    console.log(JSON.stringify(data_json));
                    $.ajax({
                        type: "POST",
                        url: "/admins",
                        data: JSON.stringify(data_json),
                        contentType: "application/json; charset = utf-8",
                        success: function (result) {
                        }
                    });
                } else {
                    alert("password mismatch")
                }
            });
        });
    </script>
</head>
<body>
<h1>ADMINS</h1>
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

<div class="row justify-content-md-center">
    <div class="col-3">
        <form>
            <div class="col-6">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" class="form-control" id="username"
                           placeholder="Username">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" name="email" id="email"
                           aria-describedby="emailHelp" placeholder="Enter email">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="firstname">Firstname</label>
                    <input type="text" name="firstname" class="form-control" id="firstname"
                           placeholder="Firstname">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="lastname">Lastname</label>
                    <input type="text" name="lastname" class="form-control" id="lastname"
                           placeholder="Lastname">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password"
                           placeholder="Password">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="phoneNumber">Phone</label>
                    <input type="number" name="phoneNumber" class="form-control" id="phoneNumber"
                           placeholder="Phone">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="confpassword">Confirm Password</label>
                    <input type="password" name="confpassword" class="form-control" id="confpassword"
                           placeholder="Password">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="dateOfBirth">Date of birth</label>
                    <input type="date" name="dateOfBirth" class="form-control" id="dateOfBirth"
                           placeholder="Date of birth">
                </div>
            </div>
            <button type="submit" id="button" class="btn btn-outline-success">Add admin</button>
        </form>
    </div>
    <div class="col-9">

    </div>
</div>
</body>
</html>