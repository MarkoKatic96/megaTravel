FROM openjdk:8
ADD target/rating-service-0.0.1-SNAPSHOT.jar rating-service.jar
EXPOSE 8900
ENTRYPOINT ["java","-jar","rating-service.jar"]