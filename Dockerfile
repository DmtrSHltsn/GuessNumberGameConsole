FROM eclipse-temurin:8-jre
WORKDIR /app
COPY GuessNumberGameConsole.class /app/
ENTRYPOINT ["java", "GuessNumberGameConsole"]
