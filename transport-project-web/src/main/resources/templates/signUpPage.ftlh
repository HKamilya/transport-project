<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Register Form</title>

</head>

<body>
<form method="POST">
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
