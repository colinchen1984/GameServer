# -*- coding: utf-8 -*- 
import socket
import struct
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(("localhost", 11113))
s.listen(5)
while(1):
	t = s.accept()[1]
	print t
	t.recv()
