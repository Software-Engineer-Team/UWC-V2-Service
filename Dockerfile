#
# Build stage
#

FROM maven:3.8.6-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:20-ea-21-oraclelinux8
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
# If use Railway deploy we don't need to use EXPOSE
EXPOSE 0.0.0.0:80:8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
