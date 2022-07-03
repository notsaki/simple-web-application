run:
	docker-compose -f ./docker/docker-compose.yml --env-file ./.env up --remove-orphans --force-recreate

build:
	docker-compose -f ./docker/docker-compose.yml --env-file ./.env build

deploy:
	./deploy.sh