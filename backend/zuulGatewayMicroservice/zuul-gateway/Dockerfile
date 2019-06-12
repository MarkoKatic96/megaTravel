FROM openjdk:8
ADD target/zuul-gateway-0.0.1-SNAPSHOT.jar zuul-gateway.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","zuul-gateway.jar"]