FROM eclipse-temurin:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/springboot3wd7d5i4/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
