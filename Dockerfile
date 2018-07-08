FROM openjdk:8-jre

VOLUME /tmp

COPY target/semantic-trajectory.jar app.jar

RUN sh -c 'touch app.jar'

ENTRYPOINT ["java", "-Duser.timezone=UTC", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]