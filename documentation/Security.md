

**1. Spring Security**
#For Getting Authenticated Current User from Authentication
For Backend
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
Optional<User> user = userService.findByEmail(auth.getName());

For Frontend
Use the dependency for thymeleaf 
<dependency>  
 <groupId>org.thymeleaf.extras</groupId>  
 <artifactId>thymeleaf-extras-springsecurity5</artifactId>  
</dependency>

Use in <html
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"




<!--stackedit_data:
eyJoaXN0b3J5IjpbMjEwNDM5NzYzNCwtMTUxMTYxNjEwNCwzMz
E5MDYzMDcsLTIxMzkzOTY2ODddfQ==
-->