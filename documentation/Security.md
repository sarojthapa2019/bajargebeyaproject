

**1. Spring Security**
#For Getting Authenticated Current User from Authentication
Authentication auth = SecurityContextHolder.getContext().getAuthentication();

Optional<User> user = userService.findByEmail(auth.getName());


<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEwMDk2MTA0OTYsLTE1MTE2MTYxMDQsMz
MxOTA2MzA3LC0yMTM5Mzk2Njg3XX0=
-->