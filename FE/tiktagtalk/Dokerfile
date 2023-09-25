#!/bin/bash
FROM node:18.17.0
WORKDIR /FE/tiktagtalk
COPY package.json .
RUN npm install 
COPY . ./
EXPOSE 3000
CMD ["npm", "start"]
