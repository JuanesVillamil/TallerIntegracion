Instrucciones:

Terminal 1:
docker-compose up --build

Terminal 2:
docker exec -it taller1-publicador-1 sh
python /app/publicador.py

Terminal 3:
docker-compose exec suscriptor /bin/sh
python /app/suscriptor.py 50

Si se quieren hacer mas pruebas para nuevas pujas es abrir una nueva terminal
docker-compose exec suscriptor /bin/sh
python /app/suscriptor.py <valor puja>