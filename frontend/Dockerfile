# develop stage
FROM node:16.3.0-alpine as develop-stage
WORKDIR /app
COPY package*.json ./
RUN npm i -g add quasar
RUN npm i -g add @quasar/cli
COPY . .
# build stage
FROM develop-stage as build-stage
#RUN npm install
#RUN quasar build
EXPOSE 8081
# production stage
CMD ["quasar", "dev"]

