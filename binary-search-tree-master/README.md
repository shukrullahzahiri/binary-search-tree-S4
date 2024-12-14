
This Java project is a Spring Boot application that creates a binary search tree 
using user input. The application features different routes, allowing users to enter a 
series of numbers separated by commas. These numbers are processed by inserting and 
balancing the tree, with both the input numbers and the resulting tree 
structure stored in a MySQL database. 
The application also displays all previous trees in JSON format.

To run the program, build the application:
Use Maven to build the Spring Boot application by running:
mvn clean package
This command will execute all five unit tests, which should return as passed.

Then run the application by executing the BinarySearchTreeApplication class to start 
the application. Access the application:
Open a web browser and navigate to:
http://localhost:8081/enter-numbers

Then, enter numbers seperated by commas to create a binary search tree.
Use the 'Show Previous' button to view earlier trees stored in the database.

