import paho.mqtt.client as mqtt
import time

broker = "mqtt.eclipseprojects.io"
port = 1883
auction_topic = "auction/bids"
result_topic = "auction/result"
auction_duration = 25  # DUracion de la puja se puede cambiar

# Crear cliente MQTT y especificar la versión de la API de callbacks
client = mqtt.Client(client_id="Publisher", protocol=mqtt.MQTTv311, transport="tcp")

# Conectar al broker
client.connect(broker, port)

# Lista para almacenar las pujas
bids = []

def on_message(client, userdata, message):
    bid = float(message.payload.decode("utf-8"))
    bids.append(bid)
    print(f"Puja recibida: {bid}")

# Asignar la función de callback para recibir mensajes
client.on_message = on_message

# Suscribirse al tema de las pujas
client.subscribe(auction_topic)

# Iniciar el bucle de red
client.loop_start()

# Iniciar la subasta
print("Iniciando la subasta para el producto X")
print(f"Tienes {auction_duration} segundos para hacer tu puja...")
time.sleep(auction_duration)

# Determinar la puja más alta
if bids:
    highest_bid = max(bids)
    print(f"La puja más alta es: {highest_bid}")
    client.publish(result_topic, f"La puja ganadora es: {highest_bid}")
else:
    print("No se recibieron pujas.")
    client.publish(result_topic, "No se recibieron pujas.")

# Detener el bucle de red
client.loop_stop()


