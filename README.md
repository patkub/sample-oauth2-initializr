# sample-oauth2-initializr

## WIP

Playing around with customizing Spring Initializr. This is very early work-in-progress.
All it does so far is create a `src/main/resources/static/custom/someCustomFile.txt` file.

TODO:
 - [ ] Customize `ResourceFileContributor` to create necessary files for an OAuth2 application


## How to use this

Java 21

### Run the app

```
./gradlew bootRun
```

### Create a new project

Create a new project in IntelliJ using this Spring Initilizr.
1. IntelliJ > New Project > Spring Boot
2. Spring Initilizr Server URL: http://localhost:8080/
