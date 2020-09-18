# mutant-service

[![Build Status](https://travis-ci.org/estebandiazm/mutant-service.svg?branch=master)](https://travis-ci.org/estebandiazm/mutant-service)
[![Coverage Status](https://coveralls.io/repos/github/estebandiazm/mutant-service/badge.svg)](https://coveralls.io/github/estebandiazm/mutant-service)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/javase-jdk11-downloads.html)
- [Gradle 6](https://gradle.org/install/)
- [Docker](https://docs.docker.com/get-docker/)
- [MongoDB](https://www.mongodb.com/es)

## Running the application locally

Run MongoDB service by Docker compose:

Run in project folder
```
docker-compose up -d
```

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

This will deploy the Service and you can see the [Swagger documentation](https://mutant-service.rj.r.appspot.com/swagger-ui.html)

[DEMO](https://mutant-service.rj.r.appspot.com/)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.

