# Getting Started

This **Spring Batch** application reads a .csv file and uploads its contents to a **Postgres** database.
The app is running in the **Docker** container.

Pre-built commands help launch the application. \
`./start.sh` \
`./stop.sh`

### Requirements
- Java 11
- Docker

Also, a Maven wrapper (mvnw) is automatically included so that you don't have to install Maven to run this project, and the Docker takes care of the Postgres.

### Run configuration

- **Start only DB in Docker** \
The configuration has been created for Intellij users, this will only help to start the database if the application is no running but the image has not been deleted yet.

### Test environment

Coming soon...

# Used references

### Useful articles from web

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Running Spring Boot with PostgreSQL in Docker Compose](https://www.baeldung.com/spring-boot-postgresql-docker)
* [Testing a Spring Batch Job](https://www.baeldung.com/spring-batch-testing-job)

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#howto-batch-applications)