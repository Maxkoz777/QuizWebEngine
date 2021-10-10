# Requirements
## Glossary
- **Game pin:** quiz identifier which can be used to connect to a quiz
- **Quiz creator:** registered user, who created the current quiz.
- **Web user:** an unregistered user, who entered the site to complete the quiz

## Stakeholders Roles
| Stakeholder’s Name    |      Roles      |  Responsibilities |
|:---------------------:|:---------------:|:-----------------:|
| Developer             |  Backend developer, Frontend developer, QA | Creating code of backend side, managing database, deploying. |
| Designer              |    Web designer |  Creating the design and layout of a website or web pages.  | 
| Project manager       | Scrum master    | Hold meetings, coach team members. |
| Product owner         | Product backlog manager | Generating features\ideas, managing and prioritizing the product backlog, accepting features. |
| User         | Product user | Actively using the product, feedback giving |

## User stories

<table cellspacing="2" border="1" cellpadding="5" width="600">
  <tr>
   <td>
<strong>
User Type</strong>
   </td>
   <td><strong>User Story Title</strong>
   </td>
   <td><strong>
User stories</strong>
   </td>
  </tr>
  <tr>
   <td rowspan="13" valign="top"><strong>Web User</strong>
   </td>
   <td valign="top">Registration
   </td>
   <td>As a stakeholder user I want to register into the application by entering my username and confirming the password so that I will have an account in the application.
   </td>
  </tr>
  <tr>
   <td rowspan="2" valign="top">Login
   </td>
   <td>As a stakeholder user I want to login into the personal account by entering my email username and the password so that only me will have an access into my account.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to logout from my personal account so that I will have an opportunity to login into another account.
   </td>
  </tr>
  <tr>
   <td rowspan="10" valign="top">Solving quizzes
   </td>
   <td>As a stakeholder user I want to find the quiz by input the game pin in special field so that quiz entering will be fast and easy.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user after entering game pin I want to go to the page with quiz so that I will solve the time-limited quiz.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click on one of the answers so that I will choose my answer to the question.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to field to be colored red or green so that I will understand that my answer is correct or not.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to see the correct answer so that I will find out the correct answer.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to have an exit button in the page with quiz so that I will have an opportunity to exit during the quiz.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to enter my username so that I will solve quiz with username.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to see time remaining during the quiz so that I will know how mush time is left to answer to the question.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to see top-3 winners at the end of quiz so that I will know winner usernames.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to copy and share the game pin by pressing the button so that others will have an access to the game.
   </td>
  </tr>
  <tr>
   <td rowspan="10" valign="top"><strong>Quiz creator</strong>
   </td>
   <td rowspan="10" valign="top">Quiz creation
   </td>
   <td>As a stakeholder user I want to create the quiz by creating questions so that I can run the quiz with these questions.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click on one of the questions so that I will open the page with question and answers.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click on the tick near the answer so that I will choose one or more right answer.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to enter the number of questions before start of creation questions so that the system will know how many questions will be in the current quiz.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to enter the quiz time by entering the number in the field so that the system will know how much time is given for answering each question.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click at the edit button at the page with particular question so that I will edit question or any answer.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click on the question in the edit mode so that I will edit it.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to click on the answer in the edit mode so that I will edit it.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to to click on the check box of the answer in the edit mode so that I will deselect or select correct answer.
   </td>
  </tr>
  <tr>
   <td>As a stakeholder user I want to all created quizzes be in the homepage so that I will see all od them in one place.
   </td>
  </tr>
</table>


## Non-functional requirements
1. **Performance.**\
The user must wait less than 3 seconds before the target operation happens (the
page renders given the 1000 of users at the moment.
1. **Portability and compatibility.**\
The application should be compatible on any device with one of following
browsers’ versions:\
IE 11+\
EDGE 12+\
FireFox 98-12+\
Chrome 21-28+\
Safari 6.1-6.8+\
Opera 12.1+
3. **Reliability.**\
The system is proposed to have 85 percent reliability for a month, this
means that during this month, under normal usage conditions, there’s an 85
percent chance that the system won’t experience critical failure.
4. **Availability.**\
The platform must be available 98 percent of the time during a month.
5. **Security.**\
All data inside the system or its part will be protected against malware
attacks or unauthorized access