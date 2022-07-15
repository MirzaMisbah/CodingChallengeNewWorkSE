# ID-Service Coding-Challenge
Hi Mirza,

this is your coding-challenge. Please read through the next steps before you start coding anything.

## Preparation
1. install all maven dependencies
2. run the application locally by executing `IdServiceApplication.main()`

## Task
Please complete this task on your own. And obviously feel free to use Google/stackoverflow etc.

In the tracking-World it is important to be able to connect the tracking-Events to the respective user. Due to 
privacy/legal and security restrictions we don't want to use our internal XingUserId for this purpose. Even better: We
would like to have an own ID for every external tool we are working with. (So we are the only one who is able to e.g.
connect the tool_a_user_id for ToolA with the tool_b_user_id for ToolB)

To tackle this problem we would like you to develop a RESTful Java Service using SpringBoot. 

### API
The service should expose an API-Endpoint to fetch the external ids for different tools by providing the internalUserId:
-> It should be possible to fetch all external IDs for a given UserId

Assume that we currently do not have any external IDs for ToolA and ToolB. This means you can just create new external
IDs if the given UserId is unknown to the service. (Feel free to use anything as ID that seems useful to you)

### Storage
The service should be able to store the IDs in a database. To keep it simple we did already add H2 as dependency.
In the end there should be a table in this format in the database:

| internal_user_id | tool_a_user_id | tool_b_user_id |
|------------------|----------------|----------------|
| 1                | random-id-123  | random-id-456  |
| 2                | random-id-234  | random-id-567  |


## Interesting Reads
### Reference Documentation
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.10/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.10/reference/htmlsingle/#boot-features-developing-web-applications)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Lombok](https://projectlombok.org/)