echo 'Stop Docker'
cd src/main/docker || exit
docker-compose down
