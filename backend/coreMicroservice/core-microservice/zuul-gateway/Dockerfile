FROM openjdk:8
ADD target/zuul-gateway-0.0.1-SNAPSHOT.jar zuul-gateway.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","zuul-gateway.jar"]