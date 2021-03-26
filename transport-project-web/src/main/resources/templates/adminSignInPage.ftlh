<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>admSignIn</title>
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
<form method="post" action="/admin">
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <label>
        <input type="checkbox" name="remember-me">Запомни меня
    </label>

    <button type="submit">Войти</button>
</form>
</body>
</html>