
# Database
## Server: Mysql
As per the project requirement we are using Mysql Server as our datasource.

## Setup
- To simulate production and development environment it is required to have a common remote database server as production database and local/remote database as development database.
- For this we are using a free remote mysql server provided by www.remotemysql.com. Each developer creates a free account on this site and can use his personalised database for local testing and one common database for production.

We use application properties as following for development and production environment.

<u>For Development</u> *(database, username and password are personalised)*
>**application-dev.properties**
>*spring.datasource.url=jdbc:mysql://remotemysql.com:3306/xtTDyNlrK1*  
>*spring.datasource.username=xtTDyNlrK1*  
>*spring.datasource.password=ekCIK3eB8W*  
>*spring.datasource.driver-class-name=com.mysql.jdbc.Driver*
>*spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect*

<u>For Production</u>
>**application-prod.properties**
>*spring.datasource.url=jdbc:mysql://remotemysql.com:3306/4Dhgn9DONW*  
>*spring.datasource.username=4Dhgn9DONW*  
>*spring.datasource.password=PIYlfVqDkZ*  
>*spring.datasource.driver-class-name=com.mysql.jdbc.Driver*
>*spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect*


## Alternatives:
There are numerous alternatives of mysql database server. Some of the best alternatives are listed below:
+  AWS - Amazon Web Service (*Free for one year*)
+ GCP - Google Cloud Platform (*Free resource of worth 300$*)
+ [www.kamatera.com](https://www.kamatera.com/express/compute/apps.php?tcampaign=35160_365074&bta=35160&nci=5443&gclid=Cj0KCQjwi7DtBRCLARIsAGCJWBp-YSUVTrod6Jo5_KPOAY7X-sJFdZzcUTo103fs5S6OQ5P7kiXt-m0aAnBnEALw_wcB#app=mySQL) (*Free one month trial and $0.005/hour for every 1GB storage*)

>**Note**: Main reason for not using these alternatives is due to lack of time for further research on setting up the environment.


> Copyright @ AwesomeGroup 2019.
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTk0MjcyNDk4NiwtMTQ1ODk1NjE4OSw3NT
I0MzUyNzksMTQxMTA3ODg4NywtMTc2NjU0NTcwNF19
-->