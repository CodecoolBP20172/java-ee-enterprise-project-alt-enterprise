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

    function populateDomWithRestaurantData (response) {
        let targetDiv = $(".restaurantListContainer");
        targetDiv.empty();
        for (let i = 0; i < response.length; i++) {
            targetDiv.append(buildRestaurantSubContainer (
                response[i].name,
                response[i].id,
                response[i].description,
                response[i].location,
                response[i].imageReference));
        }
    }

    function buildRestaurantSubContainer (restName, restId, restDescription, restLocation, restImage) {
        let wrapper = $('<div>', {"class":"item col-xs-4 col-lg-3"});
        let wrapperSubDiv = $('<div>',{"class":"thumbnail"});
        let restaurantImage = $('<img>', {"class": "group list-group-image"}).attr("src", restImage);
        let captionContainer = $('<div/>', {"class": "caption"});
        let restaurantTitleContainer = $('<h2>', {"class":"group inner list-group-item-heading"});
        let restaurantTitle = $('<a>').attr("href", '@{~/restaurants/}' + restId).text(restName);
        let restaurantDescription = $('<p>', {"class": "group inner list-group-item-text"}).text(restDescription);
        let restaurantLocationContainerWrapper = $('<div>', {"class": "row"});
        let restaurantLocationContainer = $('<div/>', {"class": "col-xs-12 col-md-6"});
        let restaurantLocation = $('<p>', {"class" : "lead"}).text(restLocation);


        wrapper.append(
            wrapperSubDiv.append(restaurantImage)
                .append(captionContainer.append(restaurantTitleContainer.append(restaurantTitle))
                    .append(restaurantDescription).append(restaurantLocationContainerWrapper.append(restaurantLocationContainer.append(restaurantLocation)))))


        return wrapper;
    }



});