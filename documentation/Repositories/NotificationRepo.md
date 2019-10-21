# Notification Repository
Notification Repository simply extends JPA Repository for Notification Domain and performs various **CRUD** operations to create, read update and delete the notification message for each users.
It consists of following major fields:
+ NoteMsg: Contains notification message.
+ Target : Contains user info of the user for whom the notification is generated.
+ ActionUrl : It is a link to action which is required by user to address the notification.
+ Priority : Can be used to distinguish high priority notifications Vs low priority.

<!--stackedit_data:
eyJoaXN0b3J5IjpbMTE0MjI0NjUyNCwtMjkyODUyODcyLDczMD
k5ODExNl19
-->