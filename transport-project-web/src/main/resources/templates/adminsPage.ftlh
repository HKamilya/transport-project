<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>admins</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
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
<form method="post" action="/admin/addAdmin">
    <div class="text-success">Hi</div>
    <input name="username" type="text" placeholder="username">
    <input name="email" type="email" placeholder="email">
    <input name="firstname" type="text" placeholder="firstname">
    <input name="lastname" type="text" placeholder="lastname">
    <input name="password" type="password" placeholder="password">
    <input name="phoneNumber" type="number" placeholder="phone">
    <input type="date" name="dateOfBirth" placeholder="date of birth">
    <button type="submit">Ок</button>
</form>
</body>
</html>