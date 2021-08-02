echo 'Replacing main artifact with repackaged archive'
./mvnw clean package -DskipTests
cp target/spring-batch-dojo-0.0.1-SNAPSHOT.jar docker

echo 'Rebuild Docker image'
cd docker || exit
docker-compose down
docker rmi spring-batch-dojo:latest
docker-compose up