$(document).ready(function(){
    $( ".restaurantButton" ).each(function( index ) {
        $(this).on("click", function() {
            var realIndex = index + 1;
            var buttonName = "restaurantDiv" + realIndex;
            $("#" + buttonName).toggle(500);
        });
    });

    $(".restaurantDiv").each(function () {
        $(this).hide();
    })
});