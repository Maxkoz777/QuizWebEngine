# QuizWebEngine

[![codecov](https://codecov.io/gh/QuizWebEngine/branch/main/graph/badge.svg)](https://codecov.io/gh/QuizWebEngine)

## :wave: About this project
This are a user-generated quizzes that can be accessed via a web browser. It can be used as learning-based platform to review students' knowledge. Using this platform you can create you own quiz and utilities in educational affairs.

We are trying to gamify the quiz procedure that users can spend time with interest benefit and we hope that you would like our application.

## :game_die: Usage
This application can help you to make you gamefy educational proccess.

## :heavy_check_mark: Business Goals and Objectives
The main objective of the software is to give the opportunity for users around the world
to use the “Quiz web engine” platform. “Quiz web engine” is an educational and entertainment
web application that will be used by people to play quizzes on different subjects and create them
for all others respectively.

The main business goals are categorized as follows:
1. **The reducing of Total Cost of Ownership**\
This goal can be achieved through the following aspects such as managing flexibility
and portability of our software, and reducing the costs of deployments and operations
2. **Improving Capability/Quality of system**\
The development of platform and application maintenance will imply the permanent support of
reliability and availability of system, increasing performance and functionality of system.

## :fire: Features
1. Creating quizzes.
2. Passing quizzes.
3. Showing leaderboard after the quiz.

## :hotsprings: Frameworks
- **Back:** java, spring boot, hibernate, PostgreSQL, Swagger
- **Front:** Vue.js, quasar
- **Deploy:** Heroku, git

## :heavy_exclamation_mark: Prerequisites
Installed Java 11 and Node.js

## :wrench: Installation
1. Clone git repository
2. Create database
3. Define 3 new environment variables:
- PSQL_DB_URL for DB url
- PSQL_DB_USER for DB user
- PSQL_DB_PASS for DB User's password
4. Open directory Backend as maven project and start, open terminal and execute `mvn clean install`
5. Open directory Frontend, Open terminal and write `npm install`, after that `quasar dev`
6. Open `localhost:8081`

## :bookmark_tabs: Requirements
Check [Requirements](/documentation/Requirements.md) file to find:
- Glossary.
- Stakeholders Roles.
- User stories.
- Non-functional requirements.

## :art: Design
Check [Design](/documentation/Design.md) file to find:
- UM diagrams (Class and Sequence).
- SOLID and Design patterns usage.

## :hammer: Architecture
Check [Architecture](/documentation/Architecture.md) file to find:
- Static view diagram
- Dynamic view diagram

## :computer: Code
- Lint\
![Lint](/documentation/diagrams/lint.png)
- Test coverage\
![Test coverage](/documentation/diagrams/test_coverage.png)

## :link: Links
- [Swagger](https://quizwebengineback.herokuapp.com/swagger-ui.html)
- [Backend](https://quizwebengineback.herokuapp.com)
- [Figma](https://www.figma.com/file/ZDGvE0XfUQobBqqStit8lj)

## :movie_camera: Demo
![Demo](/documentation/diagrams/Demo.gif)

## :v: Credits
This project was done by :

[Kostarev Grigorii](https://github.com/none-word), [Maxim Kozhinov](https://github.com/Maxkoz777), [Khamatova Regina](https://github.com/Homa3030) and [Evgeny Afanasev](https://github.com/AfanasevEvgeny).
