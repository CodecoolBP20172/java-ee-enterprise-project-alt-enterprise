$(document).ready(function(){
    $( ".restaurantButton" ).each(function( index ) {
        console.log(index);
        $(this).on("click", function() {
            var buttonName = "restaurantDiv" + index;
            $("#" + buttonName).toggle(500);
        });
    });

    $(".restaurantDiv").each(function () {
        $(this).hide();
    })
});