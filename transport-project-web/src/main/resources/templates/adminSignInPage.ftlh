<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>admSignIn</title>
</head>

<body>
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