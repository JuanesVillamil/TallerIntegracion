# queue_service.py

import socket
import threading
import queue as Queue
import time

message_queue = Queue.Queue()
last_received_time = time.time()  # Marca de tiempo de la última recepción de un mensaje

def handle_server_connection(client_socket):
    global last_received_time
    while True:
        data = client_socket.recv(1024).decode()
        if not data:
            break

        print(f" [x] Received '{data}'")
        message_queue.put(data)
        print(f" [x] Added '{data}' to the queue")
        last_received_time = time.time()  # Actualizar la marca de tiempo
    
    client_socket.close()

def start_queue_server(host='0.0.0.0', port=12345):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind((host, port))
    server.listen(5)
    print(f" [*] Listening on {host}:{port}")

    while True:
        client_socket, _ = server.accept()
        print(f" [*] Accepted connection from {client_socket.getpeername()}")
        client_handler = threading.Thread(target=handle_server_connection, args=(client_socket,))
        client_handler.start()

def send_messages_to_client(client_host, client_port):
    last_test_message_time = time.time()

    while True:
        current_time = time.time()
        
        if not message_queue.empty():
            message = message_queue.get()
            try:
                with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                    s.connect((client_host, client_port))
                    s.sendall(message.encode())
                    print(f" [x] Sent '{message}' to client at {client_host}:{client_port}")
            except ConnectionRefusedError:
                print(f" [!] Connection to {client_host}:{client_port} refused. Requeueing message.")
                message_queue.put(message)
                time.sleep(8)  # Wait before retrying
            except Exception as e:
                print(f" [!] Failed to send message to {client_host}:{client_port}: {e}")
                message_queue.put(message)
                time.sleep(10)  # Wait before retrying
        elif current_time - last_test_message_time >= 10:
            message = "Test message from queue service"
            try:
                with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                    s.connect((client_host, client_port))
                    s.sendall(message.encode())
                    print(f" [x] Sent '{message}' to client at {client_host}:{client_port}")
            except ConnectionRefusedError:
                print(f" [!] Connection to {client_host}:{client_port} refused. Retrying...")
                time.sleep(8)  # Wait before retrying
            except Exception as e:
                print(f" [!] Failed to send message to {client_host}:{client_port}: {e}")
                time.sleep(10)  # Wait before retrying
            
            last_test_message_time = current_time

def monitor_no_data_received():
    global last_received_time
    while True:
        current_time = time.time()
        if current_time - last_received_time > 5:
            print(" [!] No data received from server in the last 5 seconds")
            last_received_time = current_time  # Actualizar la marca de tiempo para evitar múltiples mensajes de advertencia
        time.sleep(5)

if __name__ == '__main__':
    import os

    queue_port = int(os.getenv('QUEUE_PORT', 12345))
    client_host = os.getenv('CLIENT_HOST', 'localhost')
    client_port = int(os.getenv('CLIENT_PORT', 22345))

    print(f" [*] Starting queue service on port {queue_port} to send messages to client at {client_host}:{client_port}")
    
    server_thread = threading.Thread(target=start_queue_server, args=('0.0.0.0', queue_port))
    server_thread.start()

    client_thread = threading.Thread(target=send_messages_to_client, args=(client_host, client_port))
    client_thread.start()

    monitor_thread = threading.Thread(target=monitor_no_data_received)
    monitor_thread.start()
