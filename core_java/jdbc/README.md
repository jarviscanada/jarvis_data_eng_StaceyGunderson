# Introduction
This java application implements a basic UI and a PSQL database for a stock portfolio. We utilized DAO patterns with JDBC to send and retrieve information in the service layer and Postgresql for the database implementation.
Data is sent via json over http and mockito and junit have been used for testing.
Maven has been used to compile the project. 

# Implementaiton

## Design Patterns
DAO, or a data access object, is a pattern for abstracting data access to the client side UI.
In this project we used DAOs for both the position and quote information, pulled from the database or used to send data to the database.
DAOs were also utilized to send data between different classes and methods within the program.

A repository design pattern is a pattern that abstracts the data layer and separates the data from the logic.
By using service objects with dao, we have implemented a repository design pattern which made it easier to mock and test within the program.
Repository design patterns can overcomplicate code, but due to the simple majority data focused design, it helped to compartmentize the code.

# Testing
The Stock Quote Controller was tested manually, different options selected and tested to make sure the UI flowed properly.

The daos are integration tested, while the service and other classes have been unit tested with mockito when necessary.