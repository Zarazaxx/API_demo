# Demo project API autotests of Miroshkina Julia.
## About project
This project demonstrates my skills in test automation using Java (API).  
It includes a scalable test framework, CI integration, and best practices for maintainability and readability.  
Resources for testing http://85.192.34.140:8080/swagger-ui/index.html
### Project Purpose
- Demonstrate strong expertise in API test automation using Java
- Showcase clean and scalable test framework architecture
- Apply best practices: modularity, reusability, maintainability
- Validate API behavior through functional, negative, and schema tests
- Simulate real-world scenarios with dynamic test data and builders
- Provide clear and actionable test reporting (Allure)

 ⚡ This project focuses on backend/API testing, with UI and mobile automation planned as future improvements.
## Tech Stack
|Category|Tools|
|---|---|
| Language: | Java |
|Build Tool:|Gradle|
|Test Framework:|JUnit|
|API Testing:|RestAssured|
|Reporting:|Allure|
|CI/CD:|Jenkins / GitHub Actions|
|Other:|Faker, Lombok|
## Architecture
Separation of concerns:
- config/ — configuration
- data/  - generate data
- help/ - auxiliary classes
- models/ - DTO 
- spec/ - specification
- tests/ - tests
- utils/ - API methods
- schemas/ - JSON schemas
```
src
 ├──.github
 │   └── workflows
 │         └── CI_test.yaml
 └── test
     ├── config
     |      └── ServerConfig.java
     ├── data
     |    ├── GameBuilder.java
     |    ├── RandomData.java
     |    └── UserBuilder.java
     ├── help
     |    └── fixtures
     |          └── TestFixtures.java
     ├── models
     |      ├── AdditionalData.java
     |      ├── Dlc.java
     |      ├── Game.java
     |      ├── InfoResponse.java
     |      ├── InfoWrapper.java
     |      ├── LoginRequest.java
     |      ├── RegistrationResponse.java
     |      ├── Requirements.java
     |      ├── Token.java
     |      ├── UserRequest.java
     |      └── UserResponse.java
     ├── spec
     |     ├── BaseApi.java
     |     └── BaseSpecs.java
     ├── tests
     |     ├── AuthApiTests.java
     |     └── UsersApiTests.java
     |
     └── utils
            ├── AuthController.java
            └── UsersController.java
```
## How to Run 

### 📦 Prerequisites
Make sure you have installed:
- Java 17
- Gradle
---
### 🚀 Run all tests

```bash
./gradlew clean test
```
### Run specific tests
```bash
./gradlew test --tests "TestClassName"
```
### Reports
```bash
./gradlew allureReport
./gradlew allureServe
```
<details> <summary>💡 Tips</summary>
Test results are stored in: build/allure-results<br>
You can configure environments via system properties<br>
Parallel execution can be enabled in Gradle config<br>
</details> 

## About me
QA Engineer with 6+ years of experience in software testing, including hands-on experience in API automation using Java.

I specialize in:
- Designing and maintaining test frameworks from scratch
- Deep understanding of backend systems and API architecture
- Writing clean, maintainable, and scalable test code

Currently, I am expanding my expertise in:
- UI test automation
- Mobile testing (Appium)
- Advanced test architecture (SDET level)

I focus on writing tests that not only validate functionality, but also improve product quality and development speed.
## Contacts
LinkedIn: [Julia Miroshkina](http://linkedin.com/in/julia-miroshkina/)
Email: [Gmail](miroshkina.j.e@gmail.com)




