# Expense Tracker Application

## Overview

The Expense Tracker application (backend) is a Spring Boot-based project designed to help users manage their expenses efficiently. The application supports user registration, authentication, and allows users to track and manage their expenses. The project is built using Spring Boot, Spring Security, and PostgreSQL.

## Features

- User registration and authentication
- Role-based access control (Admin and User)
- Secure password storage using Spring Security
- CRUD operations for users and expenses
- API endpoints for user and expense management

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- Java

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL

### Setup Instructions

1. **Clone the repository**

   ```
   git clone https://github.com/your-username/expense-tracker.git
   cd expense-tracker
   
2. **Configure the database**

   Create a PostgreSQL database named expensetracker and update the application.properties file with your database credentials.

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/expensetracker
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
   
3. **Build the project**

   ```
   mvn clean install

6. **Run the application**
   
   ```
   mvn spring-boot:run
   
   
7. **Access the application**

   Open your browser and navigate to

   ```
   http://localhost:8080.

### API Endpoints

**User Management**

  **Register User**
  
  1. **URL**
     ```
     POST /api/users/register
  
  
  2. **Request Body**
    
      ```
      {
                  "username": "johndoe",
                  "password": "password123",
                  "email": "johndoe@example.com",
                  "fullName": "John Doe",
                  "address": "123 Main St",
                  "age": 30,
                  "gender": "MALE"
      }
  
  3. **Response**
    
      ```
          
            {
              "message": "User 'John Doe' registered successfully.",
              "user": {
                "id": 1,
                "username": "johndoe",
                "email": "johndoe@example.com",
                "fullName": "John Doe",
                "address": "123 Main St",
                "age": 30,
                "gender": "MALE",
                "roles": ["USER"]
              }
           }
  
  
  **Update User**
  
  1. **URL**
     
     ```
     PUT /api/users/update/{userId}
  
  
  2. **Request Body**
          ```
          
            {
              "username": "john_updated",
              "email": "john_updated@example.com",
              "fullName": "John Updated",
              "address": "456 New St",
              "age": 31,
              "gender": "MALE"
           }
    
  3. **Response**
  
      ```
          
            {
              "message": "User 'John Updated' updated successfully.",
              "user": {
                "id": 1,
                "username": "john_updated",
                "email": "john_updated@example.com",
                "fullName": "John Updated",
                "address": "456 New St",
                "age": 31,
                "gender": "MALE",
                "roles": ["USER"]
              }
           }
  
  **Get All User**
  
  1. **URL**
     
     ```
     GET /api/users/all
  
    
  2. **Response**
  
      ```
            {
              "message": "User list fetched successfully.",
              "users": [
                {
                  "id": 1,
                  "username": "john_updated",
                  "email": "john_updated@example.com",
                  "fullName": "John Updated",
                  "address": "456 New St",
                  "age": 31,
                  "gender": "MALE",
                  "roles": ["USER"]
                },
                ...
              ]
           }
  
  
  **Delete User**
  
  1. **URL**
     ```
     
          GET /api/users/all
  
  2. **Response**
  
     ```
      {
         "message": "User 'John Updated' deleted successfully."
      }


   


## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License.

## Contact
For any questions or inquiries, please contact sumanbisunkhe304@gmail.com.

This `README.md` file includes an overview, features, technologies used, getting started instructions, API endpoint examples, and other relevant details to help users understand and contribute to the project.

 


