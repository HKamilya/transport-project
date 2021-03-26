<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Register Form</title>
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
    <div class="card text-white bg-dark mb-3" style="max-width: available;">
        <div class="card-body">
            <form method="POST">
                <div class="row justify-content-md-center">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputUsername">Username</label>
                            <input type="text" name="username" class="form-control" id="exampleInputUsername"
                                   placeholder="Username">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" name="email" id="exampleInputEmail1"
                                   aria-describedby="emailHelp" placeholder="Enter email">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                                else.</small>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputFirstname">Firstname</label>
                            <input type="text" name="firstname" class="form-control" id="exampleInputFirstname"
                                   placeholder="Firstname">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputLastname">Lastname</label>
                            <input type="text" name="lastname" class="form-control" id="exampleInputLastname"
                                   placeholder="Lastname">
                        </div>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                                   placeholder="Password">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputPhone">Phone</label>
                            <input type="number" name="phoneNumber" class="form-control" id="exampleInputPhone"
                                   placeholder="Phone">
                        </div>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputPassword1">Confirm Password</label>
                            <input type="password" name="confpassword" class="form-control" id="exampleInputPassword1"
                                   placeholder="Password">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="exampleInputDate">Date of birth</label>
                            <input type="date" name="dateOfBirth" class="form-control" id="exampleInputDate"
                                   placeholder="Date of birth">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-success">signUp</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
