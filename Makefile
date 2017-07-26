stop-all:
	docker-compose stop -t 0
	docker-compose rm -fv

docker-build:stop-all
	docker-compose build

run-docker-deps:stop-all
	docker-compose up -d db
	docker-compose up -d mq

run-docker-app:docker-build
	docker-compose run -e PORT=8080 -p 8080:8080 app

run-docker-test:docker-build
	docker-compose run test
	docker-compose stop -t 0

jenkins:
	#docker-compose -f docker-compose-extra.yml stop -t 0
	#docker-compose -f docker-compose-extra.yml rm -fv
	docker-compose -f docker-compose-extra.yml up --build jenkins
