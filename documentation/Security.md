

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

as in following demo

<html lang="en" xmlns:th="http://www.thymeleaf.org"  
 xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"  
 xmlns:sec="http://www.thymeleaf.org/extras/spring-security"  
  layout:decorate="~{views/common/masterlayout}">

just use the sec:authorize tag to check authentication as below:
sec:authorize="isAuthenticated()" 
And to check the specific role
sec:authorize="hasAuthority('ROLE_ADMIN')" 

Demo Code:

<span class="text-center" sec:authorize="hasAuthority('ROLE_ADMIN')">Issue Payment</th>


<!--stackedit_data:
eyJoaXN0b3J5IjpbMTY3ODIwNTMzNiwxOTYyMjEyNDE2LC00Nj
Y5MjA4MTQsOTU4MDY4OTkyLC0xNTExNjE2MTA0LDMzMTkwNjMw
NywtMjEzOTM5NjY4N119
-->