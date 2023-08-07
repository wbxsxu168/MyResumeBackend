# syntax=docker/dockerfile:1

FROM python:3.11-slim-buster
LABEL maintainer="wbxsxu168@hotmail.com"

ENV PROJECT_ROOT /app
WORKDIR $PROJECT_ROOT

COPY ./myProject1/requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
RUN pip3 install social-auth-app-django --upgrade

COPY . .
WORKDIR /app/myProject1
CMD ["python3", "manage.py", "runserver", "0.0.0.0:8080"]

EXPOSE 8080
