$(document).ready(function(){

    $(".reservationCreator").click(function requestReservation (event) {
        event.preventDefault();
        let data = {
            "date": $("#date-input").val(),
            "numOfPeople": $("#sel1").find(":selected").text(),
            "comment": $("#comment-text").val(),
            "restaurantId": $(".reservationCreator").data("restaurant")
        };
        $.ajax({
            type: "POST",
            url: "/api/make_reservation",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log(response)
            }
        });
        $("#exampleModal").modal('hide');
        $("#date-input").val('');
        $("#comment-text").val('');

    });

});