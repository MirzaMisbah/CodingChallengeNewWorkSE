# ID-Service Coding-Challenge

## Steps for basic implementation
1. Install all maven dependencies
2. Test the application locally
3. Test database connection
4. Set contructor and variables in model -> User.java
5. Write logic/query in controller -> UserController.java

	5.1. Set databse credntials & base url
	
	5.2. Create an API Request
	
		5.2.1. Register JDBC Driver
		5.2.2. Open a DB connection
		5.2.3. Execute Query
		5.2.4. Extract data (for read queries)
		5.2.5. Close connection
		
6. Take care of exceptions (especially JDBC, class.forName)
7. Write an additional method to drop table (for saving time during testing) 

## Generated requests
GET http://localhost:8080/user/ExternalIDs

GET http://localhost:8080/user/ExternalIDs?internal_user_id=1
