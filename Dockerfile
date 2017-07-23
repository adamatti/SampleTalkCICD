FROM gradle:4.0.1-jdk8-alpine

USER root
VOLUME "/home/root/.gradle"

#RUN mkdir /app
WORKDIR /app

#add gradle wrapper (if needed)
#ADD gradle /app
#ADD gradlew /app
ADD system.properties /app

#cache dependencies
ADD build.gradle /app
RUN gradle build idea --stacktrace --no-daemon

ADD . /app
RUN gradle install -x test --no-daemon

CMD /app/build/install/sample/bin/sample
