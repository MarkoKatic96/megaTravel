FROM openjdk:8
ADD target/agent-global-service-0.0.1-SNAPSHOT.jar agent-global-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","agent-global-service.jar"]