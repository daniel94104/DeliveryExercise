_# Delivery Parsing 
## Design Overview

Based on the description, there are models need to be stored and perform calculation on it, also present the result. It is a good use case of MVC pattern. 

![Component Overview](DesignView.png)

## Programming Language And Framework

Picked Java and SpingBoot framework. 

Java is OOP language, good fit when there is requirement to implement object behaviors. 

SpringBoot offers range of easy-use tools such as Hibernate, Thymeleaf. It is time saving approach to implement a full stack project within short time period.

## How to run

The project is developed in Intellij IDE. If you open the code repo with Intellij,  there is existing configuraiton to run the application. Automated unit tests are located in the test folder. 

1. Right click on the test folder to run the tests within IDE. 
2. Use maven to run test cases. Open the maven tab, click the verify command to run. 

### Run the application

After the maven dependency is installed automatically in the IDE, click the run button with the provided run configuration on the top of the IDE. 

1. Access the default report RestFul APi:
    Can use Postman, issue a GET call on `http://localhost:8808/report`.

2. Access the WebUi. Can open website on `http://localhost:8808/index`.

### Connect to the database
Select h2 in the select your database section

Credentials
- username=sa
- password=password

- https://dbeaver.com/docs/wiki/Create-Connection/


