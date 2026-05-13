# Build stage: compile Java code with Maven
FROM maven:3.9-amazoncorretto-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage: minimal Amazon Corretto image
FROM amazoncorretto:11-alpine AS runtime
WORKDIR /app
COPY --from=build /app/target/lambda-consulta-convenios-1.0.0.jar .
# Entry point for Lambda-style local testing (AWS Lambda Java base images use different entry points)
CMD ["java", "-cp", "lambda-consulta-convenios-1.0.0.jar", "com.semillero.convenios.Handler"]
