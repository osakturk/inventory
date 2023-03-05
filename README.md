# Product Inventory Project

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/#build-image)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/#data.nosql.mongodb)
* [MongoDB](https://www.mongodb.com/docs/manual/reference/)
* [Docker](https://docs.docker.com/reference)
* [Kotlin](https://kotlinlang.org/docs/home.html)
* [Swagger](https://swagger.io/docs)
* [JUnit](https://junit.org/junit5/docs/current/user-guide/)
* [SonarQube](https://docs.sonarqube.org/latest)
* [MockK](https://mockk.io)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
* [Test Containers](https://www.testcontainers.org)

### Values
* Mongo DB is running on 27017 port. You should use this syntax for connection:
  ```bash
  mongodb://username:password@host:port/databasename
  ```
* Inventory application is running on [8080](http://localhost:8080) port


### Containerizing and Deployment
* In this project we have 1 docker file and 1 docker compose file.
* Docker file includes maven download, maven build command and java jar file build
  * You have a option for maven manual build. You have to go to the project root path and run the following command:
  ```bash
    mvn clean install
  ```
  * This command will build your application and create a jar file that you can use manually.
* Docker compose file includes 2 different images
* The first image is Mongo DB. Mongo DB image needs 2 different files.
  * [.env](.env) file includes mongo db version, container name and spring boot ports.
  * [inventory.js](inventory.js) file includes database user creation
* The second image is Inventory Application image created by the [DockerFile](Dockerfile)
  * Application image depends on Mongo DB instance. If the application gets an error, it will restart the application by itself
  * You can add another environment variable to environment tab. You can use like an [application.properties](src/main/resources/application.properties) element
* If you want to run this project on a docker container, you just need to run this command:
  ```bash 
    docker compose up -d
  ```
* Stop the container(s) using the following command:
  ```bash 
    docker compose down -d
  ```
* Delete all volumes using the following command:
  ```bash 
    docker volume rm $(docker volume ls -q)
  ```
* Delete all containers using the following command:
  ```bash 
    docker rm -f $(docker ps -a -q)
  ```

### Swagger
* After startup, you can access the Swagger documentation site with [this](http://localhost:8080/swagger-ui.html) URL

### Postman Collection Url
* After startup, you should import [this](src/main/resources/Inventory%20Collection.postman_collection.json) file to Postman Application.
  With these requests, you can successfully create products, articles and orders.
