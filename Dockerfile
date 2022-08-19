FROM adoptopenjdk/openjdk15
EXPOSE 8080
ARG JAR_FILE=target/movie-appV2-0.0.1-SNAPSHOT.jar
ADD $JAR_FILE movie-app-v2.jar
ENTRYPOINT ["java", "-jar", "/movie-app-v2.jar"]