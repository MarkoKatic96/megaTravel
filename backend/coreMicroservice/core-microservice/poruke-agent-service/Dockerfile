FROM openjdk:8
ADD target/poruke-agent-service-0.0.1-SNAPSHOT.jar poruke-agent-service.jar
EXPOSE 8700
ENTRYPOINT ["java","-jar","poruke-agent-service.jar"]