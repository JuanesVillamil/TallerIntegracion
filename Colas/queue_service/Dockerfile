# Usar la imagen base de Python
FROM python:3.9

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo queue_service.py al contenedor
COPY queue_service.py .

# Comando para ejecutar la cola
CMD ["python", "queue_service.py"]
