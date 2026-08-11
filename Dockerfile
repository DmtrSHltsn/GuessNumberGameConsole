FROM openjdk:8-jre-slim
WORKDIR /app
COPY GuessNumberGameConsole.class /app/
ENTRYPOINT ["java", "GuessNumberGameConsole"]
