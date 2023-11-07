FROM openjdk:21
LABEL maintainer="tosha.ryabchuk@gmail.com"
ADD target/secuirty-0.0.1-SNAPSHOT.jar security1.jar
ENTRYPOINT ["java", "-jar", "security1.jar"]