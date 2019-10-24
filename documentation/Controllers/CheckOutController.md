#Handles all checkout activities

###Methods within CheckOutController:
1. @GetMapping("/cart/checkout")
public String checkOut(Model model): directs to the checkout
2. @GetMapping("/cart/checkout/order")
 public String placeOrder(Model model): directs to the placeOrder
3. @GetMapping("/order/list") public String orderHistory(Model model): displays customer's order history
4. @GetMapping("/order/cancel/{id}") public String cancelOrder(@PathVariable Long id): cancels the order
<!--stackedit_data:
eyJoaXN0b3J5IjpbMjE1NzUwMzMwXX0=
-->