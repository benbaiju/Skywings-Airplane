# Airplane Employee Management System 

## Introduction

The Airplane Employee Management System is a web application designed to manage employee data in an airplane company. This system provides functionality to create, read, update, and delete employee records using a REST API. Additionally, it includes a search feature that allows users to find employees by name. To enhance scalability and communication, this system also implements a message queue using ActiveMQ.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: HTML, CSS, Bootstrap, JavaScript
- **Message Queue**: ActiveMQ
- **Database**: MySQL

## Features

### 1. REST API for Employee Management

The system provides a RESTful API for managing employee records, which includes the following operations:

- **Create**: Add a new employee record.
- **Read**: Retrieve employee records by ID and search by name.
- **Update**: Modify existing employee records.
- **Delete**: Remove employee records.

### 2. Employee Search

- Users can search for employees by name, which helps quickly locate specific employees within the system.

### 3. Message Queue Implementation

- The system uses ActiveMQ to implement a message queue for asynchronous communication, which can be used for various purposes such as notifications, event handling, and data synchronization.

### 4. Frontend Interface

- The frontend is designed using HTML, CSS, Bootstrap, and JavaScript to provide an intuitive user interface for interacting with the system.

## Getting Started

To set up and run the Airplane Employee Management System on your local machine, follow these steps:

1. Clone the repository (if available) or download the source code.

2. Install any necessary dependencies, such as Java, Maven, ActiveMQ, and MySQL.

3. Configure the system, including the database connection and ActiveMQ settings.

4. Build and run the Spring Boot backend.

5. Launch the frontend by opening the HTML file in a web browser.

6. Start ActiveMQ and configure the message queue settings.

## Configuration

Ensure that you configure the following aspects of the system:

- **Database Configuration**: Configure the MySQL database connection by specifying the database URL, username, and password in the application's configuration files.

- **ActiveMQ Configuration**: Set up ActiveMQ with the necessary queues and topics. Update the configuration files accordingly.

## Usage

1. Access the web application through a web browser by opening the HTML file.

2. Use the frontend interface to interact with the system, including creating, reading, updating, and deleting employee records.

3. Utilize the search feature to find employees by name.

4. Explore the message queue functionality by sending and receiving messages.


## License

This project is licensed under the [MIT License](LICENSE.md).



Thank you for using the Airplane Employee Management System. We hope this system helps streamline employee management in your airplane company.

