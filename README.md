# spring-lilearning-2021
Java Spring: My Java Spring self-written solutions from my spring LinkedIn Learning coursework in 2021. 

### Index
There are two separate projects in this repository. 

1.  The **room-web-app** from Frank P. Moley's "*Spring Boot 2.0 Essential Training*" Course on LinkedIn Learning. 

2.  The **fizzbuzz-web-app** that I created as an exercise from that course. 

- See folder *room-web-app* for the room website for using Swing with services

- See folder *room-web-app-sec*  for the room website that uses authentication to access the room listings.
<br>Password for this fake site is: password, Username for this fake site is: admin

- See folder *room-web-app-msngconsumer* for the room website (security removed) that can send messages

- See *fizzbuzz-web-app* for my own fun attempt at building a Fizzbuzz web app with a service that can be used to play the fizzbuzz game. 

To run the RabbitMQ application for the room-web-app-msngconsumer module of this repository, you will need to download and install Docker.
Docker must be used to run rabbit for this module to work. 
Docker is not needed for the other modules at this time. 

Scripts are provided in src/main/resources/
The script start_rabbit.sh will start RabbitMQ
The script stop_rabbit.sh will stop RabbitMQ
*End of readme*
