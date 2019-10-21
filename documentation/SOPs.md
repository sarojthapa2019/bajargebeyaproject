
# Standard Operating Procedures (SOPs)

## Naming

 - Use camelCase for properties, variables and methods
 - For interfaces, use sentence case without the I. eg. UserService
 - implementation class - suffix should be impl and name should represent respective service interface. (all the implementation classes should be within the respective impl subfolder.)
 - controllers - suffix should be controller
 - repository - use JpaRepository
 - For domain/entity relationship names
	 - use singular nouns to indicate the entity on the one side
	 - plular nouns (lists) to indicate the entity on the many side
## Code documentation
 - Add comments/documentation to classes, interfaces, methods, and when required fields/variables
 - The methods in Service and Repository interfaces that are self explanatory do not need to be documented but if there are custom queries in Repository or Complex business logic in Service it requires documentation
 - For comments/documents, add the following in the comments/documents before classes, interfaces, methods, etc.
	 - Name
	 - Date
	- Author
	- Modification history (when applicable)
	- Description
## Date and time
 - use LocalDate as a type
> Written with [StackEdit](https://stackedit.io/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbNTk3NTUwNDc1LDE2NjQ2NzgzNDcsLTE3ND
IyMzIwMCwxNDAyMzQ2OTA5LC0xNzI2MTMxMjc5LDk3MTc4NTE1
NywtODM5NzU2MTkwXX0=
-->