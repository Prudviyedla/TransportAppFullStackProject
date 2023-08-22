FROM openjdk:11.0.10-jre
#Creating a working directory
WORKDIR usr/src
#setting environment variables
ENV MONGODB_DATABASE TransportDB
ENV MONGODB_URL mongodb://localhost:27017/TransportDB
#Copy jar file from target directory to working directory
ADD ./target/TransportFavouritesService-0.0.1-SNAPSHOT.jar /usr/src/TransportFavouritesService-0.0.1-SNAPSHOT.jar
#Run the jar file
ENTRYPOINT ["java","-jar","TransportFavouritesService-0.0.1-SNAPSHOT.jar"]