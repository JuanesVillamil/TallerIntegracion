# Taller 1 Arqui - Rest

## Pasos para ejecutar el proyecto:

### DOCKER
1. Desde la terminal, ir directo a la raiz del proyecto
2. Ejecutar el comando mvn clean install para generar el archivo .jar
3. Luego de generar el archivo .jar, ejecutar el siguiente comando para generar la imagen: docker build -t arquisoft/taller1-rest .
4. Una vez generada la imagen, ejecutar el siguiente comando para correrla: docker run -p 8080:8080 arquisoft/taller1-rest

### POSTMAN
Con la herramienta postman, realizar 2 request a los servicios creados:
   - Obtener subastas:
     GET
     URL: http://localhost:8080/api/subasta/list
   - Realizar oferta:
     POST
     URL: http://localhost:8080/api/oferta/ofertar
     Cuerpo del request:
     {
        "valor": "13000.12",
        "subastaId": 1,
        "postorId": 3
     }
