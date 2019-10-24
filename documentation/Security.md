

**1. Spring Security**
#For Getting Authenticated Current User from Authentication

Authentication auth = SecurityContextHolder.getContext().getAuthentication();
Optional<User> user = userService.findByEmail(auth.getName());


<!--stackedit_data:
eyJoaXN0b3J5IjpbLTYwNTQ2MTUzMiwtMTUxMTYxNjEwNCwzMz
E5MDYzMDcsLTIxMzkzOTY2ODddfQ==
-->