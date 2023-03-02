FROM eclipse-temurin:17.0.3_7-jdk

COPY target/inventory.jar /

EXPOSE 8080

ADD target/inventory.jar /inventory.jar

CMD exec java -jar inventory.jar