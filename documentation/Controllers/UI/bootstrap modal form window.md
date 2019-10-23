

# Sample code

### button to trigger

    <button type="button" class="btn button button_continue trans_200" data-toggle="modal"  
      data-target="#addReviewModal">Add Review  
    </button>

### the modal form

 

    <div id="addReviewModal" class="modal fade" role="dialog">  
     <div class="modal-dialog">  
     <div class="modal-content">  
     <form action="#" method="post" th:action="@{/products/{id}/reviews/add(id=${product.id})}"  
      th:object="${review}"  
      enctype="multipart/form-data">  
     <input type="hidden" th:field="*{product.id}" th:value="${product.id}">  
     <div class="modal-header">  
     <button type="button" class="close" data-dismiss="modal">&times;</button>  
     </div> <div class="modal-body">  
      
     <h2 class="footer_about_text">Add Review</h2>  
     <div class="form-group">  
     <label for="description">Description:</label>  
     <textarea class="form-control" type="text" id="description" th:field="*{description}"  
      placeholder="Description"></textarea>  
     </div>  
     <div class="form-group">  
     <label for="rating">Rate:</label>  
     <select class="form-control" id="rating" th:field="*{rating}">  
     <option th:value="5">5</option>  
     <option th:value="4">4</option>  
     <option th:value="3">3</option>  
     <option th:value="2">2</option>  
     <option th:value="1">1</option>  
     </select> </div> </div> <div class="modal-footer">  
     <div class="form-group">  
     <input type="submit" class="btn button button_continue trans_200" id="add_review"  
      value="Save"  
      data-="modal">  
     <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>  
     </div> </div> </form> </div> </div></div>

### the javascript submit button handler

    <script type="text/javascript">  
      function form_submit() {  
            document.getElementById("add_review").submit();  
      }  
    </script>

> Written with [StackEdit](https://stackedit.io/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbODEzNDIzMTFdfQ==
-->