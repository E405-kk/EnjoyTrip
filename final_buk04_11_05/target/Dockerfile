FROM openjdk:18
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9201
ENTRYPOINT ["java","-jar","app.jar"]
