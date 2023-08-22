FROM openjdk:11.0.14-jre
WORKDIR usr/scr
ENV MONGODB_DATABASE TransportDB
ENV MONGODB_URL mongodb://localhost:27017/TransportDB
ADD ./target/DirectionApi-0.0.1-SNAPSHOT.jar /usr/scr/DirectionApi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","DirectionApi-0.0.1-SNAPSHOT.jar"]