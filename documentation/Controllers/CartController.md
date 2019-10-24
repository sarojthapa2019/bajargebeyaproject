#CartController controls activities of collecting product to the cart
Methods within the CartController:

1. @GetMapping("/gone")
 public String clear(SessionStatus status)  clear cart  session

2. @ModelAttribute public void commonCartAttributes(Model model): provides common model for shopping cart

3. @GetMapping("/index") public String index(Model model): display cart by index

4. @GetMapping("/cart")  public String showCart(Model model): show the cart

5.   @GetMapping("/cart/items/{id}")
       @ResponseBody
       public  HashMap<String, Object>  addEntryToCart(@PathVariable("id") Long productId, Model model)
6. @PostMapping(value = "/cart/items/quantity")                          
Adds each item to the cart /for changing the quantity of each products in a cart entry
    public @ResponseBody HashMap<String, Object> updateQuantity(@RequestBody String  data, Model model) 

7. for clearing cart item
    @GetMapping("/cart/clear")
    public String clearCart(Model model)
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE2ODkzMTU3MzNdfQ==
-->