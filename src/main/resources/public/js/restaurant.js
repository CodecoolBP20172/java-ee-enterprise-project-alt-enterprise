$(document).ready(function(){
    $( ".restaurantButton" ).each(function( index ) {
        $(this).on("click", function() {
            var id = $(this).attr('id');
            var buttonName = "restaurantDiv" + id;
            $("#" + buttonName).toggle(500);
        });
    });

    $(".restaurantDiv").each(function () {
        $(this).hide();
    })

    $(".locationSorterButton").click(function requestRestaurantData (event) {
        let data = {
            "location": $(event.target).html()
        };
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/api/get_restaurant_by_location",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                populateDomWithRestaurantData(response);
                console.log(response)

            }
        });
    });

    function populateDomWithRestaurantData (response) {
        $(".restaurantContainer").empty();

    }

    function buildRestaurantSubContainer (restName, restId,) {
            let wrapper = $('<div>');
            let wrapperSubDiv = $('<div>');
            let restaurantButton = $('<a>', {"class": "btn-default text-center form-control restaurantButton"}).text(restName).id(restId);
            let subContainer = $('<p/>', {"class": "quantity-number"}).text(quantity);
            let plusBtn = $('<button>', {"class": "quantity-changer btn btn-primary", "data-change": "plus"}).text("+");
            let priceParagraph = $('<p/>', {"class": "col-2"}).text(price);
            wrapper
                .append(wrapperSubDiv)
                .append(restaurantButton)
                .append(quantityParagraph)
                .append(plusBtn)
                .append(priceParagraph);

            return wrapper;
    }



});