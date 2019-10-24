#DashboardController
 It's the dashboard of the the Application.
#Methods within the DashboardController
1. public String getDashboard(Model model): allows user to select the  pages based on the role
2. public String approveSeller(@PathVariable("id") Long id, @PathVariable("bool") Boolean bool): allows the Admin to approve the seller 
3. public ResponseEntity<InputStreamResource> citiesReport(): generates user report

 