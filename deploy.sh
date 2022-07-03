rm -rf simple-web-application-client
rm -rf client

git clone https://github.com/notsaki/simple-web-application-client.git
mv simple-web-application-client client

cp ./client/.env.example ./client/.env
printf "API_URI=http://0.0.0.0:6868" > ./client/.env

cd client && yarn install && yarn build
cd ../

cp -R ./client/dist/* ./src/main/resources/static

docker-compose -f ./docker/docker-compose.yml --env-file .env up --remove-orphans --build --force-recreate