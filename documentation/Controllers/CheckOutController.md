#Handles all checkout activities

###Methods within CheckOutController:
1. public String checkOut(Model model): directs to the checkout
2. public String placeOrder(Model model): directs to the placeOrder
3. public String orderHistory(Model model): displays customer's order history
4. public String cancelOrder(@PathVariable Long id): cancels the order