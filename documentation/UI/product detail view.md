
# Product detail view

    <!-- Product Info -->  
    <div class="col-lg-6 product_col">  
     <div class="product_info">  
     <div class="product_name" th:text="${product.name}">Cool Clothing with Brown Stripes</div>  
     <div class="product_category">In Category: <span th:each="category : ${product.categories}"  
      th:text="${category.name}">Category</span>  
     </div>  <!--                        <div class="product_rating_container d-flex flex-row align-items-center justify-content-start">-->  
     <!--                            <div class="rating_r rating_r_4 product_rating"><i></i><i></i><i></i><i></i><i></i></div>--> <!--                        </div>-->  <div class="product_price" th:text="${'$'+product.unitPrice}">$3<span>.99</span></div>  
     <div class="product_size">  
     <div class="product_size_title">Unit</div>  
     <p th:text="${product.unit}">XS</p>  
     </div> <div class="product_size">  
     <div class="product_size_title">Stock</div>  
     <p th:text="${product.stock}">0</p>  
     </div> <div class="product_size">  
     <div class="product_size_title">Seller</div>  
     <p th:text="${product.seller}">N/A</p>  
     <a class="btn btn-warning"  
      th:href="@{/users/{id}(id=${product.seller})}" href="#">Follow</a>  
     </div> <div class="product_size">  
     <div class="product_size_title">Review</div>  
     <p th:text="${product.description}">N/A</p>  
     </div> <div class="product_buttons">  
     <div class="text-right d-flex flex-row align-items-start justify-content-start">  
     <div class="product_button product_cart text-center d-flex flex-column align-items-center justify-content-center">  
     <div> <div><img src="../static/images/cart.svg" th:src="@{/images/cart.svg}"  
      class="svg" alt="">  
     <div>+</div>  
     </div> </div> </div> </div> </div> </div></div>

> Written with [StackEdit](https://stackedit.io/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE1NDkwMjc2NTZdfQ==
-->