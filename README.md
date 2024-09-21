# TestAPI Takhaty Mobile App

-Takhaty is a mobile app focused on psychological problems and provides solutions to overcome them. My role was to test the API for this APP using Rest Assured.
TestAPI Takhaty Mobile App.
An Automation API Testing project that tests user registration, login, logout, and OTP verification for a mobile app. This project contains both main application code and end-to-end (E2E) tests.
## Table of Contents
1. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Running Tests](#running-tests)
5. [Project Structure](#project-structure)
6. [Contributing](#contributing)
---

## Features
- User Registration and OTP verification
- User Login and Logout
- End-to-End (E2E) testing framework for registration and login processes

---

## Installation

### Prerequisites
- Java 8 or higher
- Maven
- Git

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/marwankhalil99/TestAPI_TakhatyMobApp.git
   cd TestAPI_TakhatyMobApp
   ```

2. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

---

## Usage

1. Build the project:
   ```bash
   mvn clean package
   ```

2. Run the application (if applicable):
   ```bash
   java -jar target/your-app.jar
   ```

---

## Running Tests

### To run unit and E2E tests:
```bash
mvn test
```

Test classes for registration and login are located under the `src/test/java` directory:
- `RegisterationProcess.java`: End-to-End test for the registration process
- `LogIn.java`: Tests for the login process
- `LogOut.java`: Tests for logout functionality
- `VerifyRegOTP.java`: Tests for OTP verification during registration

---

## Project Structure

```
TestAPI_TakhatyMobApp/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── Login/
│   │           └── LoginUser/
│   │           └── ResponseBody/
│   │       └── Registeration/
│   └── test/
│       └── java/
│           └── E2E_Test/
│           └── TestRegisteration/
├── pom.xml
├── .gitignore
└── .idea/
```

- **`src/main/java`**: Contains the main source code, including login, registration, and OTP verification classes.
- **`src/test/java`**: Contains test cases for verifying the registration and login processes.
- **`pom.xml`**: Maven project file for managing dependencies.
- **`.gitignore`**: Specifies intentionally untracked files to ignore.
- **`Test Results - Registeration.html`**: HTML file containing test results.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

---

## Contact

For any inquiries, feel free to reach out:

- Email:marwan.shamso2022@gmail.com
- GitHub: marwankhalil99
