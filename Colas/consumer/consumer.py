import socket
import os

def receive_message(port):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(('0.0.0.0', port))
    server.listen(5)
    print(f" [*] Listening on 0.0.0.0:{port}")

    while True:
        client_socket, _ = server.accept()
        message = client_socket.recv(1024).decode()
        if message:
            print(f" [x] Received '{message}'")

if __name__ == '__main__':
    port = int(os.getenv('CLIENT_PORT', 22345))
    receive_message(port)
