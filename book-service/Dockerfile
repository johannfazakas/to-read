# two step build not working anymore due to jooq domain generation
#FROM gradle:jdk17 as build
#USER gradle
#WORKDIR /app
#COPY --chown=gradle:gradle build.gradle.kts .
#COPY --chown=gradle:gradle src src
#RUN gradle bootJar

FROM openjdk:17-slim
WORKDIR /app
#COPY --from=build /app/build/libs/book-service.jar .
COPY build/libs/book-service.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "book-service.jar"]
