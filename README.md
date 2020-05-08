# This is an example API test project for [Covid API](https://api.covid19api.com)

This project uses

- Java 8 or above
- [REST Assured ](http://rest-assured.io/)
- Junit 5
- Allure 

# Prerequisites

- Install Java 8
- [Refer REST Assured](https://github.com/rest-assured/rest-assured/wiki/GettingStarted)

## System Under Test (SUT)
* The SUT should be available at [https://api.covid19api.com](https://api.covid19api.com)

# Executing tests

### All tests

#### mac or linux
````
./gradlew clean test
````
#### windows
````
gradlew.bat clean test
````
Allure report will be available in directory build/reports/allure-report