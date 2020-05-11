# This is an example API test project for [Covid API](https://api.covid19api.com)

This root project uses

- Java 8 or above
- [REST Assured ](http://rest-assured.io/)
- Junit 5
- Allure 

Subproject uses

- Java 8
- Junit 5
- [Karate](https://github.com/intuit/karate)

# Prerequisites

- Install Java 8
- [Refer REST Assured](https://github.com/rest-assured/rest-assured/wiki/GettingStarted)

## System Under Test (SUT)
* The SUT should be available at [https://api.covid19api.com](https://api.covid19api.com)

# Executing rest assured tests

#### Endpoint for SUT could be changed in `config.properties` file

### All tests

#### mac or linux
````
./gradlew clean test
````
#### windows
````
gradlew.bat clean test
````
Source code for rest assured tests available in directory src/test/java/org/test/covid_api/

Allure report will be available in directory build/reports/allure-report

# Executing karate tests

### All tests

#### mac or linux
````
./gradlew :karate_example:clean :karate_example:test
````
#### windows
````
gradlew.bat :karate_example:clean :karate_example:test
````
Source code for karate tests available in directory karate-example/src/test/java/covid_test/
Cucumber report will be available in directory karate-example/build/cucumber-html-reports/