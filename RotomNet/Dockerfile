FROM maven:3.8.5-openjdk-17-slim as builder 
WORKDIR /project
COPY pom.xml /project/
RUN mvn clean verify
COPY /src /project/src
RUN mvn package -o

FROM openjdk:17.0-slim-buster
WORKDIR /urs/src/app/
COPY --from=builder /project/target/*.jar /urs/src/app/
EXPOSE 8080
CMD [ "java", "-jar", "RotomNet-0.0.1-SNAPSHOT.jar" ]
