# UML Diagrams
## Class diagram
![Class diagram](/documentation/diagrams/class_diagram.png)

### Sequence Diagram
- Pass quiz diagram\
  ![Pass quiz](/documentation/diagrams/pass_quiz_diagram.png)
- Create quiz diagram\
  ![Create quiz](/documentation/diagrams/create_quiz_diagram.png)

## SOLID pattern
- **S** - Single-responsibility Principle
Every method and class is responsible for a certain task. Therefore, there are no long methods and no widely used services. For example, createAnswersForQuestion method create only answers for question, nothing more.

- **O** - Open-closed Principle
We created methods by extending, and we didn't modify existing ones.

- **L** - Liskov Substitution Principle
We used some methods from extended classes. That applies to this principle. We can use some methods from JpaRepository when use UserRepository for searching in database.

- **I** - Interface Segregation Principle
We implemented only those methods, which were used in other methods. There are no unused methods in repositories and services in our application.
- **D** - Dependency Inversion Principle
We used JPA Repositories, but we didn't depend on low-level DB connection.

## Design patterns
- **Facade**\
Pattern is used to simplify the data we exchange between back-end and front-end. There is a class UserDTO which has only necessary fields web-site may need while communicating to the server. In our actual database additionally we store email, password, user roles and creation time of the user. All this fields client doesn't need, so we use simplified version of the class User mentioned earlier - UserDTO.  So for transforming actual user from db into its lightweight version we use UserFacade class with single method userToUserDTO.
- **Observer**\
We used an observer design pattern for reaction to people's actions. This pattern is widely used in the frontend part of our application. For example, when we should confirm a password, the code catch differences between the first instance and the second one. If two passwords do not match with each other, front notify users about that.
