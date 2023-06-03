FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app

COPY target/myapp.jar /app

ENV SPRING_DATA_MONGODB_HOST=mongodb
ENV SPRING_DATA_MONGODB_PORT=27017
ENV SPRING_DATA_MONGODB_DATABASE=mydb

CMD ["java", "-jar", "myapp.jar"]