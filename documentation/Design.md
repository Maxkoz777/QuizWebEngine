# UML Diagrams
## Class diagram
TBA

### Sequence Diagram
- Pass quiz diagram\
  ![Pass quiz](/documentation/diagrams/pass_quiz_diagram.png)
- Create quiz diagram\
  ![Create quiz](/documentation/diagrams/create_quiz_diagram.png)

## SOLID pattern
TBA

## Design patterns
- **Facade**\
Pattern is used to simplify the data we exchange between back-end and front-end. There is a class UserDTO which has only necessary fields web-site may need while communicating to the server. In our actual database additionally we store email, password, user roles and creation time of the user. All this fields client doesn't need, so we use simplified verison of the class User mentioned earlier - UserDTO.  So for transforming actual user from db into its lightweight version we use UserFacade class with single method userToUserDTO.
- **Observer**\
Example: Password checking on the frontend side.
