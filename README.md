# to-read

## Build & run

### Docker
```bash
cd book-service
docker build -t book-service .

docker network create --driver bridge to-read-network
docker run -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin --name postgres-db --net=to-read-network -d postgres:14-alpine
docker run -p 8015:8080 --net=to-read-network -d book-service
```

### Docker compose
```bash
docker compose build
docker compose up
```