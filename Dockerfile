FROM openjdk:8
ADD target/e-commerce-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]