echo 'Stop Docker'
cd docker || exit
docker-compose down

echo 'Delete unnecessary logs'
cd ..
rm -rfv bin/apache-ftpserver/res/log/*