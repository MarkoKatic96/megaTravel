FROM openjdk:8
ADD target/agent-global-back-0.0.1-SNAPSHOT.jar agent-global-back.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","agent-global-back.jar"]