FROM openjdk:8
ADD ./demo-0.0.1-SNAPSHOT.jar /tmp/demo.jar
WORKDIR /tmp
CMD java -jar demo.jar
