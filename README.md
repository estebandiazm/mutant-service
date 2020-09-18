# mutant-service

[![Build Status](https://travis-ci.org/estebandiazm/mutant-service.svg?branch=master)](https://travis-ci.org/estebandiazm/mutant-service)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/javase-jdk11-downloads.html)
- [Gradle 6](https://gradle.org/install/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.marvel.mutanservice.MutantServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/) like so:

```shell
 ./gradlew bootRun --args='--spring.profiles.active=dev'
```

## Deploying the application to GCP App Engine

The easiest way to deploy the sample application to GCP App Engine is to use the [GCP SDK](https://cloud.google.com/sdk/install?hl=es-419):

Run in project folder
```shell
gcloud app deploy
```

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.

