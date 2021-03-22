<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>admins</title>
</head>
<body>
<h1>ADMINS</h1>
<form method="post" action="/admin/addAdmin">
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