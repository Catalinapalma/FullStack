FROM eclipse-temurin:21-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_FullStack /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=buildstage /app/target/*.jar /app/publicaciones.jar

COPY Wallet_FullStack /app/wallet

ENV TNS_ADMIN=/app/wallet

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/publicaciones.jar"]