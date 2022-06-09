
FROM openjdk:17.0.2


ADD build/libs/YourDollarYourGif-0.0.1-SNAPSHOT.jar YourDollarYourGif-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "YourDollarYourGif-0.0.1-SNAPSHOT.jar"]