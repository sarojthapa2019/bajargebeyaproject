
$(document).ready(function () {

   $('.product_cart').click(function (event) {
       event.preventDefault();
       var item = $(this);
       var productId = item.attr('data-value');
       alert(productId);

       $.ajax ({
           url: '/cart/items/'+productId,
           type: "POST",
           dataType: "json",
           contentType: "application/json",

           complete: function(responseData, status, xhttp){
               $('#cart-item-count').text('('+responseData.responseJSON.count+')');
           }
       });
   })
});
