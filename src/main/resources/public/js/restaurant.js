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
});