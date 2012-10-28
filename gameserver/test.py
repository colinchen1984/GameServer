import socket
import struct
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("localhost", 11111))
pack = struct.pack("h", 0)
pack += struct.pack("q", 1024)
result = struct.pack("h", len(pack))
result += pack
pack = result
s.send(pack)
s.recv(1)