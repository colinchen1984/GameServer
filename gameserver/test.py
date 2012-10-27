import socket
import struct
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("localhost", 11111))
pack = struct.pack("h", 10)
pack += struct.pack("h", 10)
pack += struct.pack("q", 1024)
s.send(pack)
s.recv(1)