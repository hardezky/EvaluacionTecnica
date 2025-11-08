# Etapa 1: Build de Angular
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: Nginx para servir la app
FROM nginx:alpine
COPY --from=build /app/dist/credit-frontend/browser /usr/share/nginx/html

EXPOSE 80
