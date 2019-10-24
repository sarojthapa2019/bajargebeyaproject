
$(document).ready(function () {
//get the clicked product id and call rest api
   $('.product_cart').click(function (event) {
       event.preventDefault();
       var item = $(this);

       var productId = item.attr('data-value');
        // alert(productId);

       $.ajax ({
           url: '/cart/items/'+productId,
           type: "GET",
           dataType: "json",
           contentType: "application/json",

           complete: function(responseData, status, xhttp){
               console.log(responseData);
               $('#cart-item-count').text('('+responseData.responseJSON.count+')');
           }
       });
   })
});
