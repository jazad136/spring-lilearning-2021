# spring-lilearning-2021
Java Spring: My Java Spring self-written solutions from my spring LinkedIn Learning coursework in 2021. 

### Index
There are three separate projects in this repository. 

1.  The **room-web-app** from Frank P. Moley's "*Spring Boot 2.0 Essential Training*" Course on LinkedIn Learning. 

2.  The **fizzbuzz-web-app** that I created as an exercise from that course. 

3.  (New Oct. 2021) <br> The **explore-cali** application from Mary Ellen Bowman's <br> "*Creating Your First Spring Boot Microservice*" and <br>"*Extending Securing and Dockerizing Spring Boot Microservices*" courses on LinkedIn Learning  

#### All three of these projects feature backend website designs. <br>(Really loving the content that I find these guys provide on Spring)

#### Folder Descriptions
- See folder *room-web-app* for the room website for using Swing with services

- See folder *room-web-app-sec*  for the room website that uses authentication to access the room listings.
<br>Password for this fake site is: password, Username for this fake site is: admin

- See folder *room-web-app-msngconsumer* for the room website (security removed) that can send messages

- See *fizzbuzz-web-app* for my own fun attempt at building a Fizzbuzz web app with a service that can be used to play the fizzbuzz game. 

- See folder *explorecali-deploy* for the Explore California demo website that implements Mockito tests, unit tests and integration tests. 

- See folder *explorecali-security* for the Explore California website that implements on top of deploy, a security wrapper restricting access to API 
endpoints using Spring Security, and upgrades the system to use JWT authentication.

To run the RabbitMQ application for the room-web-app-msngconsumer module of this repository, you will need to download and install Docker.
Docker must be used to run rabbit for this module to work. 
Docker is not needed for the other modules at this time. 

Scripts are provided in src/main/resources/
The script start_rabbit.sh will start RabbitMQ
The script stop_rabbit.sh will stop RabbitMQ
*End of readme*
