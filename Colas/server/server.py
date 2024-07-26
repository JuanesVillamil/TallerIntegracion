# server.py

import socket
import time

def send_message(queue_host, queue_port, message):
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((queue_host, queue_port))
            s.sendall(message.encode())
            print(f" [x] Sent '{message}' to {queue_host}:{queue_port}")
    except Exception as e:
        print(f" [!] Failed to send '{message}' to {queue_host}:{queue_port}: {e}")

if __name__ == '__main__':
    queues = {
        'queue_1': ('queue_service1', 12345),
        'queue_2': ('queue_service2', 12346),
        'queue_3': ('queue_service3', 12347),
    }

    messages = {
        'queue_1': 'Hello from server to queue_1',
        'queue_2': 'Hello from server to queue_2',
        'queue_3': 'Hello from server to queue_3'
    }

    while True:
        for queue_name, message in messages.items():
            queue_host, queue_port = queues[queue_name]
            print(f" [>] Trying to send '{message}' to {queue_host}:{queue_port}")
            send_message(queue_host, queue_port, message)
            time.sleep(1)  # Wait a bit before sending the next message
        time.sleep(5)  # Wait before sending the batch of messages again
