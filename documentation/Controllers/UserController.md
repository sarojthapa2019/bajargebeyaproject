#Manage user related requests and response

# Methods within the UserController
1.  @GetMapping("/") public String index(Model model):
 GetMapping method displaying the index
2.   @GetMapping("/{id}")  public String indexUser(@PathVariable("id") Long id, Model model)