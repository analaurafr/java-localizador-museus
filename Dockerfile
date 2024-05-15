# Estágio de construção
FROM maven:3-openjdk-17 AS build-image
WORKDIR /to-build-app

# Copia os arquivos de configuração e o pom.xml
COPY pom.xml .
COPY src ./src

# Instala as dependências e cria o pacote JAR
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

# Estágio final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o JAR gerado no estágio anterior para a pasta de trabalho final
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]

