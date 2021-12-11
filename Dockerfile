FROM openjdk:8
ADD target/Producer-0.0.1-SNAPSHOT.jar Producer-0.0.1-SNAPSHOT.jar
EXPOSE 8768
ENTRYPOINT ["java","-jar","Producer-0.0.1-SNAPSHOT.jar"]