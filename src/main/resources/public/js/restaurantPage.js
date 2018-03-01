$(document).ready(function(){

    $( ".add_food_to_cart" ).each(function( index ) {
        $(this).on("click", function() {
            let data = {
                "foodId": $(this).attr('id'),
                "userId": $(this).data('user_id'),
                "restaurantId": $(".restaurant_information").attr('id')
            };
            console.log(data);
            $.ajax({
                type: "POST",
                url: "/api/order_food",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    console.log(response)

                }
            });
        });
    });

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