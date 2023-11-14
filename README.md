# Library Management System

## Overview
The Library Management System is a Java-based application designed to manage book loans in a university library. It allows students to borrow and return books while providing library staff with efficient tools to manage book inventory.

## Features
- Borrow and return books
- Track available and borrowed books
- Register new students to the system

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven (for dependency management and running tests)

### Installing and Running
1. Clone the repository:
   ```
   git clone https://github.com/UniversityLibrary/LibraryManagementSystem.git
   ```
2. Navigate to the project directory and compile the project:
   ```
   cd LibraryManagementSystem
   mvn compile
   ```
3. To run tests, use:
   ```
   mvn test
   ```

## Usage
The system is used through its main class `LibraryManagementSystem`. Here's a simple example of how to use it:

```java
public class Main {
    public static void main(String[] args) {
        LibraryDatabase db = new LibraryDatabaseImpl();
        LoanManager loanManager = new LoanManager(db);

        // Add books, register students, and manage loans...
    }
}
```

## Contributing
We welcome contributions to this project. If you find any bugs or have suggestions, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.