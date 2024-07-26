import paho.mqtt.client as mqtt
import sys

broker = "mqtt.eclipseprojects.io"
port = 1883
auction_topic = "auction/bids"
result_topic = "auction/result"

# Crear cliente MQTT y especificar la versión de la API de callbacks
client = mqtt.Client(client_id="Subscriber", protocol=mqtt.MQTTv311, transport="tcp")

# Conectar al broker
client.connect(broker, port)

def on_message(client, userdata, message):
    result = message.payload.decode("utf-8")
    print(f"Resultado de la subasta: {result}")

# Asignar la función de callback para recibir el resultado de la subasta
client.on_message = on_message

# Suscribirse al tema del resultado de la subasta
client.subscribe(result_topic)

# Verificar si se proporcionó una puja como argumento
if len(sys.argv) != 2:
    print("Uso: python suscriptor.py <monto_puja>")
    sys.exit(1)

try:
    bid = float(sys.argv[1])
    if bid <= 0:
        print("La puja debe ser un número positivo.")
        sys.exit(1)
except ValueError:
    print("Entrada inválida. Por favor, ingresa un número válido.")
    sys.exit(1)

# Publicar la puja
client.publish(auction_topic, str(bid))
print(f"Puja enviada: {bid}")

# Iniciar el bucle de red
client.loop_forever()





