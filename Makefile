stop-all:
	docker-compose stop -t 0
	docker-compose rm -fv

docker-build:stop-all
	docker-compose build

run-docker-app:docker-build
	docker-compose run -e PORT=8080 -p 8080:8080 app

run-docker-test:docker-build
	docker-compose run test
