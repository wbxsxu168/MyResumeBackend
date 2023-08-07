FROM openjdk:19-oracle
LABEL maintainer="wbxsxu168@hotmail.com"

ENV PROJECT_ROOT /usr/app
WORKDIR $PROJECT_ROOT

COPY ./target/restapidemo.jar restapidemo.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","restapidemo.jar"]
