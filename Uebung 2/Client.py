import socket
import os

def Main():

	#host = '127.0.0.1'
	host = input("Servername? -> ")
	port = 80
	
	sock = socket.socket()
	sock.connect((host,port))
	filename = input("Filename? -> ")
	if filename != 'q':
		
		sock.send(filename.encode('utf-8'))
		data = sock.recv(1024)
		data = data.decode('utf-8')
		if data[:6] == 'EXISTS':
			filesize = int(data[6:])
			message = input("File Exists, " + str(filesize) + "Bytes, download? (Y/N)? -> ")
			if message == 'Y':
				ok = 'OK'
				sock.send(ok.encode('utf-8'))
				f = open('new_'+filename, 'wb')
				data = sock.recv(1024)
				totalRecv = len(data)
				f.write(data)
				while totalRecv < filesize:
					data = sock.recv(1024)
					totalRecv += len(data)
					f.write(data)
				print("download complete")
		else: 
			print("File does not exist")
if __name__ == '__main__':
	Main()