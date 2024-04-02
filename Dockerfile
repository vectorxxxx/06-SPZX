FROM centos7-openjdk17
MAINTAINER vectorx

EXPOSE 8512
VOLUME /tmp

ADD target/spzx-1.0-SNAPSHOT.jar /spzx-1.0-SNAPSHOT.jar
RUN bash -c 'touch /spzx-1.0-SNAPSHOT.jar'
ADD backup/01.jpg /01.jpg

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "spzx-1.0-SNAPSHOT.jar"]
