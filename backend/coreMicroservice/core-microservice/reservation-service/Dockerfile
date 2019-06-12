FROM openjdk:8
ADD target/reservation-service-0.0.1-SNAPSHOT.jar reservation-service.jar
EXPOSE 8300
ENTRYPOINT ["java","-jar","reservation-service.jar"]