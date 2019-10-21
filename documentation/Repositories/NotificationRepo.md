# Notification Repository
Notification Repository simply extends JPA Repository for Notification Domain and performs various **CRUD** operations to create, read update and delete the notification domain object for respective users also termed as target users.
Notification Class consists of following major fields:
+ Message: Contains notification message.
+ Target : Contains user info of the user for whom the notification is generated.
+ ActionUrl : It is a link to action which is required by user to address the notification.
+ Priority : Can be used to distinguish high priority notifications Vs low priority.

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE5OTA2MDU3NzIsLTI5Mjg1Mjg3Miw3Mz
A5OTgxMTZdfQ==
-->