
# Order Success Page

    <!DOCTYPE html>  
     <html lang="en" xmlns:th="http://www.thymeleaf.org/"  
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{layout/main_layout}">  
     <head> <title>Checkout</title>  
      <!--<meta charset="utf-8">-->  
     <!--<meta http-equiv="X-UA-Compatible" content="IE=edge">--> <!--<meta name="description" content="Bajar Gebeya template">--> <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->  <link rel="stylesheet" type="text/css" href="../styles/bootstrap-4.1.2/bootstrap.min.css">  
     <link href="../plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">  
     <script src="../static/js/jquery-3.2.1.min.js" th:src="@{/js/jquery-3.2.1.min.js}"></script>  
     <script rel="script" src="../static/js/checkout.js" th:src="@{/js/checkout.js}"></script>  
      <!--   <script type="application/javascript" rel="script" src="js/myCart.js"></script>-->  
      <link rel="stylesheet" type="text/css" href="../styles/checkout.css">  
     <link rel="stylesheet" type="text/css" href="../styles/checkout_responsive.css">  
     </head> <body> <div layout:fragment="main">  
      
     <div class="home">  
     <div class="home_container d-flex flex-column align-items-center justify-content-end">  
     <div class="home_content text-center">  
     <div class="home_title">Product checkout</div>  
     <div class="breadcrumbs d-flex flex-column align-items-center justify-content-center">  
     <ul class="d-flex flex-row align-items-start justify-content-start text-center">  
     <li><a href="#" th:href="@{/}">Home</a></li>  
     <li>Product checkout</li>  
     </ul> </div> </div> </div> </div> <br>  <!-- Order -->  
      <div>  
     <table class="table">  
     <thead> <tr> <th>  S.N.  
                                </th>  
     <th>  Order Date  
                                </th>  
     <th>  Products  
                                </th>  
     <th>  Receipt Id  
                                </th>  
     <th>  Status  
                                </th>  
     <th>  Action  
                                </th>  
     </tr> </thead> <tbody> <div th:if="${#lists.isEmpty(productOrders)}">  
      Sorry You haven't ordered anything yet.  
                            </div>  
     <tr th:unless="${#lists.isEmpty(productOrders)}" th:each="p,i : ${productOrders}">  
     <td th:text="${i.count}"></td>  
     <td th:text="${p.orderDate}"></td>  
     <td > <span th:each="c: ${p.cartEntries}" th:text="${c.product.name}"></span>  
     </td> <td th:text="${p.receipt.id}"></td>  
     <td th:text="${p.status}"></td>  
     <td class="text-center">  
      
     <a th:if="${p.status == 'pending'}" class="btn btn-outline-success" data-toggle="modal" data-target="#modal-warning" th:attr="data-target='#modal-warning'+${p.id }">Cancel</a>  
     <a th:unless="${p.status == 'pending'}" class="btn btn-dark">Cancel</a>  
      
     <div class="modal modal-warning fade in" th:id="modal-warning+${p.id }" >  
     <div class="modal-dialog">  
     <div class="modal-content">  
     <div class="modal-header">  
     <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
     <span aria-hidden="true">×</span></button>  
     <h5 class="modal-title">Cancel Order?</h5>  
     </div> <div class="modal-body">  
     <h3>Are you sure want to cancel this order?</h3>  
     </div> <div class="modal-footer">  
     <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>  
     <a class="btn btn-danger" th:href="@{/order/cancel/{id}(id=${p.id})}"><i class="fa fa-check"></i>&nbsp;Yes</a>  
     </div> </div> </div> </div> </td>  
     </tr> </tbody> </table> </div>  
     </div>  
      
      
     <script src="js/jquery-3.2.1.min.js"></script>  
     <script src="styles/bootstrap-4.1.2/popper.js"></script>  
     <script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>  
     <script src="plugins/greensock/TweenMax.min.js"></script>  
     <script src="plugins/greensock/TimelineMax.min.js"></script>  
     <script src="plugins/scrollmagic/ScrollMagic.min.js"></script>  
     <script src="plugins/greensock/animation.gsap.min.js"></script>  
     <script src="plugins/greensock/ScrollToPlugin.min.js"></script>  
     <script src="plugins/easing/easing.js"></script>  
     <script src="plugins/parallax-js-master/parallax.min.js"></script>  
     <script src="js/checkout.js"></script>  
     </body> </html>

> Written with [StackEdit](https://stackedit.io/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTcxNjE0NDk5NV19
-->