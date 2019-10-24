#DashboardController
 It's the dashboard of the the Application.
#Methods within the DashboardController
1.  @GetMapping("/dashboard")  public String getDashboard(Model model): allows user to select the  pages based on the role
2.  @GetMapping("/seller/{id}/{bool}")  public String approveSeller(@PathVariable("id") Long id, @PathVariable("bool") Boolean bool): allows the Admin to approve the seller 
3.   @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
                produces = MediaType.APPLICATION_PDF_VALUE)public ResponseEntity<InputStreamResource> citiesReport(): generates user report

 
<!--stackedit_data:
eyJoaXN0b3J5IjpbNTA4ODU1NzVdfQ==
-->