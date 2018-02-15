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
        let targetDiv = $(".restaurantListContainer");
        targetDiv.empty();
        for (let i = 0; i < response.length; i++) {
            targetDiv.append(buildRestaurantSubContainer (response[i].name, response[i].id, response[i].description, response[i].location));
        }
    }

    function buildRestaurantSubContainer (restName, restId, restDescription, restLocation) {
            let wrapper = $('<div>', {"class":"item col-xs-4 col-lg-3"});
            let wrapperSubDiv = $('<div>',{"class":"thumbnail"});
            let restaurantImage = $('<img>', {"class": "group list-group-image"}).attr("src", "http://placehold.it/400x250/000/fff");
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