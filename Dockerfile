FROM openjdk:19-oraclelinux8
ADD target/Filmy-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar Filmy-0.0.1-SNAPSHOT.jar --envname=prod