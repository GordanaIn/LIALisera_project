FROM openjdk:17
COPY ./target/liserabackend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]