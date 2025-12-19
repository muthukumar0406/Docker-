FROM node:18-alpine

WORKDIR /app

COPY server.js .

RUN npm init -y && npm install express

EXPOSE 3002

CMD ["node", "server.js"]
