# Demoblaze Test Automation Project

This project contains automated tests for the Demoblaze e-commerce website using Selenium WebDriver, Cucumber, and Java.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Test Features](#test-features)
- [Dependencies](#dependencies)
- [Troubleshooting](#troubleshooting)
- [Disclaimer](#disclaimer)

## Prerequisites

- Java JDK 17 or higher
- Maven 3.6 or higher
- Chrome browser installed
- ChromeDriver (matching your Chrome browser version)

## Project Structure

```
demoblaze/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/         # Page Object Model classes
│   │   │   ├── elements/      # Web element locators
│   │   │   └── utils/         # Utility classes
│   │   └── resources/         # Main resources
│   └── test/
│       ├── java/
│       │   ├── hooks/         # Cucumber hooks
│       │   ├── runners/       # Test runners
│       │   └── stepDefinitions/ # Step definitions
│       └── resources/
│           └── features/      # Cucumber feature files
└── pom.xml                    # Maven configuration
```

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd demoblaze
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

3. Configure ChromeDriver:
   - Download ChromeDriver matching your Chrome browser version from [ChromeDriver Downloads](https://sites.google.com/chromium.org/driver/)
   - Add ChromeDriver to your system PATH or place it in the project's `src/test/resources/drivers` directory

## Running Tests

1. Run all tests:
   ```bash
   mvn clean test
   ```

2. Run specific feature (e.g., login tests):
   ```bash
   mvn clean test -Dcucumber.filter.tags="@login"
   ```

## Test Reports

After test execution, you can find:

1. Cucumber HTML Report:
   - Location: `target/cucumber-reports.html`
   - Open in a web browser to view detailed test results
   - Contains step-by-step execution details
   - Includes screenshots for failed scenarios

2. Screenshots:
   - Location: `target/screenshots/`
   - Contains screenshots of failed scenarios
   - Named with pattern: `scenario_failure_[scenario_name]_[timestamp].png`

## Test Features

The project includes tests for:

1. Login Functionality:
   - Successful login with valid credentials
   - Failed login attempts with invalid credentials
   - Input validation for empty fields
   - Special character handling
   - SQL injection prevention tests
   - Error message validation

2. Sign Up Functionality:
   - New user registration
   - Duplicate username validation
   - Input validation

3. Cart Functionality:
   - Add products to cart
   - Remove products from cart

4. Home Page Functionality:
   - Product navigation
   - Contact form
   - About us section

## Dependencies

Key dependencies include:
- Selenium WebDriver 4.15.0
- Cucumber 7.14.0
- JUnit 4.13.2
- SLF4J for logging
- WebDriverManager 5.5.3

For detailed dependency information, see [pom.xml](pom.xml)

## Troubleshooting

1. If tests fail to start:
   - Ensure all dependencies are properly installed

2. For other issues:
   - Review the Cucumber HTML report
   - Check screenshots in `target/screenshots`

## Disclaimer

This project is intended for **demonstration purposes only** and is not an official test suite for Demoblaze. It serves as an example of test automation practices using Selenium WebDriver, Cucumber, and Java. The tests and their results should not be considered as official validation of the Demoblaze website's functionality. 