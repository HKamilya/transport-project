
var cities = []

function getCities() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/cities",
        contentType: "application/json; charset = utf-8",
        success: function (result) {
            cities = result
        }
    })
}