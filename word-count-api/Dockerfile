FROM openjdk:11
ADD target/docker-word.jar docker-word.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "docker-word.jar"]