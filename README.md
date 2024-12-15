# Spring Boot Application

## Overview

This is a Spring Boot application built with Java and Maven. It provides a RESTful API for managing resources.

## Features

- RESTful API endpoints
- CRUD operations
- Integration with a database
- Exception handling
- Logging

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher
  
## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/ParsaKeerthana/MentorService.git
   cd MentorService
2. Build the project:  
mvn clean install
3. Run the application:
mvn spring-boot:run


## Test results
mvn clean test

## Install Allure Command-Line Tool if you want to see tests results in allure report

1. On mac: brew install allure
2. mvn clean test -> The test results will be stored in the target/allure-results directory.
3. allure serve target/allure-results

