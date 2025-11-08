FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app

COPY pom.xml ./
RUN mvn -q -B dependency:resolve

COPY . .
RUN mvn -q -B clean test jacoco:report

CMD ["/bin/bash"]
