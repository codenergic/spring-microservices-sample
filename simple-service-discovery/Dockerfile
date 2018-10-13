FROM openjdk:8-jdk-alpine
VOLUME /data
ARG JAR_FILE=*.jar
ADD target/${JAR_FILE} /app/app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar