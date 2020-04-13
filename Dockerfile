FROM openjdk:8
ADD target/weather-forecast.jar weather-forecast.jar
EXPOSE 1111
ENTRYPOINT ["java", "-jar", "weather-forecast.jar"]
