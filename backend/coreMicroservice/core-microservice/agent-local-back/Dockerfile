FROM openjdk:8
ADD target/agent-local-back-0.0.1-SNAPSHOT.jar agent-local-back.jar
EXPOSE 8500
ENTRYPOINT ["java","-jar","agent-local-back.jar"]