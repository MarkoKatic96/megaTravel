FROM openjdk:8
ADD target/admin-service-0.0.1-SNAPSHOT.jar admin-service.jar
EXPOSE 8300
ENTRYPOINT ["java","-jar","admin-service.jar"]