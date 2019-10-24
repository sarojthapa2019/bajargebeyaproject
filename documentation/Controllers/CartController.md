#CartController controls activities of collecting product to the cart
Methods within the CartController:
1. public String clear(SessionStatus status)-clear session
2. public void commonCartAttributes(Model model): provides common model for shopping cart
3. public String index(Model model): display cart by index

4. public String showCart(Model model): show the cart
5. public  HashMap<String, Object>  addEntryToCart(@PathVariable("id") Long productId, Model model): 
Adds each item to the cart 
6.  public String clearCart(Model model): clears items from the cart

