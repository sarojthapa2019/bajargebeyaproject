

**1. Spring Security**
#For Getting Authenticated Current User from Authentication

Authentication auth = SecurityContextHolder.getContext().getAuthentication();
Optional<User> user = userService.findByEmail(auth.getName());

Use the dependency for thymeleaf
<dependency>  
 <groupId>org.thymeleaf.extras</groupId>  
 <artifactId>thymeleaf-extras-springsecurity5</artifactId>  
</dependency>




<!--stackedit_data:
eyJoaXN0b3J5IjpbNTY5OTkxMzc0LC0xNTExNjE2MTA0LDMzMT
kwNjMwNywtMjEzOTM5NjY4N119
-->