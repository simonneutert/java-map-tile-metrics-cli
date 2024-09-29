FROM gradle:8-jdk21 AS build

WORKDIR /app

COPY ./gradle /app/gradle
COPY settings.gradle gradlew ./
RUN ./gradlew --version

ADD . .
RUN ./gradlew clean && ./gradlew build

FROM gcr.io/distroless/java21-debian12:latest

WORKDIR /app

COPY --from=build /app/app/build/libs/*.jar /app/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
CMD [ "[{ \"x\": 1, \"y\": 1}]" ]