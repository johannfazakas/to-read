# to-read

## Build & run

### Docker
```bash
cd book-service
# jar creation separated from image build due to jooq's build time dependency to running db
./gradlew clean bootJar
docker build -t book-service .

docker network create --driver bridge to-read-network
docker run -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin --name postgres-db --net=to-read-network -d postgres:14-alpine
docker run -p 8015:8080 --net=to-read-network -d book-service
```

### Docker compose
```bash
# jar creation separated from image build due to jooq's build time dependency to running db.
cd book-service
./gradlew clean bootJar
cd ../

docker compose build
docker compose up
```