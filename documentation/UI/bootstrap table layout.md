

# Bootstrap table layout

    <table class="table">  
     <thead> <tr> <th>Id</th>  
     <th>Name</th>  
     <th>Action</th>  
     </tr> </thead> <tbody> <tr th:each="category : ${categories}">  
     <td th:text="${category.id}">1</td>  
     <td th:text="${category.name}">Watch</td>  
     <td> <button type="button" class="btn btn-outline-success" th:attr="data-target=${'#update' + category.name + '-'+category.id}" data-toggle="modal">Edit</button>  
     <button type="button" class="btn btn-outline-danger" th:attr="data-target=${'#delete' + category.name + '-'+category.id}" data-toggle="modal">Delete</button>  
      
      <!-- Update category form -->  
      <div th:id="${'update'+category.name + '-'+category.id}" class="modal fade" role="dialog">  
     <div class="modal-dialog">  
     <div class="modal-content">  
     <div class="modal-header">  
     <button type="button" class="close" data-dismiss="modal">&times;</button>  
     </div> <div class="modal-body">  
     <form action="#" method="post" th:action="@{/products/category/update}" th:object="${category}">  
     <h3 class="footer_about_text">Update Category</h3>  
     <div class="form-group">  
     <label for="name">Name:</label>  
     <input class="form-control" type="text" th:name="name" th:id="*{name}" th:value="${category.name}">  
     <input type="hidden" th:id="*{id}" th:name="id" th:value="${category.id}">  
     </div> </div> <div class="modal-footer">  
     <div class="form-group">  
     <input type="submit" class="btn button button_continue trans_200" id="update_category" value="Update" data-="modal">  
     <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>  
     </form> </div> </div> </div> </div> </div>  
      <!-- Delete form -->  
      <div th:id="${'delete'+category.name + '-'+category.id}" class="modal fade" role="dialog">  
     <div class="modal-dialog">  
     <div class="modal-content">  
     <div class="modal-header">  
     <button type="button" class="close" data-dismiss="modal">&times;</button>  
     </div> <div class="modal-body">  
     <h3 class="footer_about_text">Are you sure you want to delete this product?</h3>  
     </div> <div class="modal-footer">  
     <div class="form-group">  
     <a class="btn btn-danger" data-="modal" th:href="@{/products/category/delete/{id}(id=${category.id})}" href="#">Yes</a>  
     <button type="button" class="btn btn-default" data-dismiss="modal">No</button>  
     </form> </div> </div> </div> </div> </div>  
     </td> </tr> </tbody></table>

> Written with [StackEdit](https://stackedit.io/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTgwMzg3MzMxXX0=
-->