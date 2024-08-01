# GitHub-Api

## Overview
This application, developed in Java using the Spring Framework, leverages the GitHub API to retrieve and analyze user and repository data. The core functionality includes:

User and Repository Information: Fetches detailed information about GitHub users and their repositories, excluding forked repositories.
Branch Details: Retrieves branch names and the latest commit SHA for each non-forked repository.
The application utilizes a RESTful client to interact with the GitHub API, processes JSON responses, and structures the data for comprehensive analysis. It is designed to provide developers with valuable insights into repository structures and their latest commits.

## Table of Contents
- [Technologies](#technologies)
- [Project Setup](#project-setup)
- [Tools Needed](#tools-needed)
- [Installation](#installation)
- [Testing the API](#testing-the-api)
- [Unit Testing](#unit-testing)
- [Feedback](#feedback)

## *TECHNOLOGIES*

### Frameworks and Libraries:
- **Spring Boot Starter Data JPA**: Helps interact with the database.
- **Spring Boot Starter Web**: Manages RESTful web applications.
- **Spring Boot Starter Test**: Provides testing libraries and tools for Spring Boot applications.
- **Lombok**: Simplifies Java code writing.

### Database:
- **H2 Database**: An in-memory database used for testing and development.

### Testing Frameworks:
- **JUnit**: A widely-used testing framework for Java.
- **Mockito**: A popular mocking framework for unit tests in Java.

## *PROJECT SETUP*

- **Java Version**: 21
- **Dependency Management**: Maven
- **Build Tool**: Spring Boot Maven Plugin

## *TOOLS NEEDED*

To run this Spring Boot project, you'll need:

- **Java Development Kit (JDK) 21**: Required for Spring Boot 3.2.5. Download from [Oracle JDK](https://www.oracle.com/pl/java/technologies/downloads/#java21) or [OpenJDK](https://openjdk.java.net/).

- **Integrated Development Environment (IDE)**: Recommended IDEs include [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/), or [NetBeans](https://netbeans.apache.org/download/index.html).

- **Maven**: Manages dependencies for Java projects. Download from [Maven](https://maven.apache.org/download.cgi).

- **Postman**: For testing APIs. Download from [Postman](https://www.postman.com/downloads/).

### Setting up the Project in IntelliJ IDEA:

1. **Import the Project:**
    - Open IntelliJ IDEA.
    - Go to `File` > `Open` and select the root directory of the project.
    - Choose the `pom.xml` file if prompted to import the Maven project.

2. **Configure JDK:**
    - Go to `File` > `Project Structure` > `Project`.
    - Ensure the Project SDK is set to JDK 21. If JDK 21 is not listed, click `Add SDK` and select the JDK installation directory.

3. **Build the Project:**
    - Right-click on the project root directory in the `Project` view.
    - Select `Maven` > `Reload Project` to ensure all dependencies are correctly loaded.
    - Build the project by clicking `Build` > `Build Project`.

4. **Run the Application:**
    - Locate the main application class (e.g., `GithubApiApplication`) in the `src/main/java` directory.
    - Right-click on the class and select `Run` to start the Spring Boot application.

## *TESTING THE API*

To test the API endpoints, you can use Postman or any other API testing tool.

1. **Open Postman** and create a new request.

2. **Set up the request:**
    - **Method:** `GET`
    - **URL:** `http://localhost:8080/users/{username}/repos`, where `{username}` is the GitHub username whose repositories you want to check.

3. **Set up the request headers:**
    - **Header Name:** `Accept`
    - **Header Value:** `application/json`

4. **Send the Request:**
    - Click on the `Send` button.

5. **View the Response:**
    - Postman will display the response containing information about GitHub repositories and their branches for the provided username.

## *UNIT TESTING*

The project uses JUnit and Mockito for unit testing. Tests can be run using your IDE's test runner or from the command line using Maven.

- **To run tests from the command line:**
    - Execute `mvn test`.

- **To run tests in IntelliJ IDEA:**
    - Right-click on the test class or method and select `Run`.

## *FEEDBACK*

If you have any feedback, questions, or issues with the project, please open an issue in the [GitHub repository](<repository-url>) or contact the project maintainers.

