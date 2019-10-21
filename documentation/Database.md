
# Database
## Server: Mysql
As per the project requirement we are using Mysql Server as our datasource.

## Setup
- To simulate production and development environment it is required to have a common remote database server as production database and local/remote database as development database.
- For this we are using a free remote mysql server provided by www.remotemysql.com. Each developer creates a free account on this site and can use his personalised database for local testing and one common database for production.
>**application.properties**
>*spring.datasource.url=jdbc:mysql://remotemysql.com:3306/xtTDyNlrK1*  
>*spring.datasource.username=xtTDyNlrK1*  
>*spring.datasource.password=ekCIK3eB8W*  
>*spring.datasource.driver-class-name=com.mysql.jdbc.Driver*
>*spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect*

We use application properties as following for development and production environment.
-application-dev.properties (development)
-application-prod.properties (production)


> Copyright @ AwesomeGroup 2019.
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTQxMTA3ODg4NywtMTc2NjU0NTcwNF19
-->