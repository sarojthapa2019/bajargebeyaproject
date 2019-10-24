

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

Use 
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"

as in following demo

<html lang="en" xmlns:th="http://www.thymeleaf.org"  
 xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"  
 xmlns:sec="http://www.thymeleaf.org/extras/spring-security"  
  layout:decorate="~{views/common/masterlayout}">

just use the sec 
<span sec:authorize="isAuthenticated()">  
<a sec:authorize="hasAuthority('ROLE_ADMIN')"


<!--stackedit_data:
eyJoaXN0b3J5IjpbMjAwODQ5MDY2OCwtMTUxMTYxNjEwNCwzMz
E5MDYzMDcsLTIxMzkzOTY2ODddfQ==
-->