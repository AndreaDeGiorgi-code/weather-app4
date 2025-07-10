FROM openjdk:17-jdk-slim

# Imposta la variabile d'ambiente per il nome dell'applicazione
ARG JAR_FILE=target/weather-app-1.0-SNAPSHOT.jar

# Copia il file JAR nell'immagine Docker
COPY ${JAR_FILE} app.jar

# Espone la porta su cui l'applicazione Spring Boot è in ascolto
EXPOSE 8080

# Comando per eseguire l'applicazione
ENTRYPOINT ["java", "-jar", "/app.jar"]