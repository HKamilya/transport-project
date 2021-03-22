<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>flights</title>
</head>
<body>
<h1>FLIGHTS</h1>
<form method="post" action="/admin/addFlight">
    <input name="countryFrom" type="text" placeholder="departure country">
    <input name="cityFrom" type="text" placeholder="departure city">
    <input name="airportFrom" type="text" placeholder="departure airport">
    <input name="countryTo" type="text" placeholder="country of arrival">
    <input name="cityTo" type="text" placeholder="city of arrival">
    <input name="airportTo" type="text" placeholder="airport of arrival">
    <input name="planeType" type="text" placeholder="airplane type">
    <input name="date1" type="text" placeholder="departure date">
    <input name="time1" type="text" placeholder="departure time">
    <input name="date2" type="text" placeholder="date of arrival">
    <input name="time2" type="text" placeholder="time of arrival">
    <input name="countOfPlaces" type="number" placeholder="count of passengers">
    <button type="submit">Ок</button>

</form>
</body>
</html>