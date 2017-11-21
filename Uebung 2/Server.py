import socket
import threading
import os.path

def RetrFile(name, sock):
	filename = sock.recv(1024)
	filename = filename.decode('utf-8')
	if os.path.isfile(filename):
		message = 'EXISTS '+ str(os.path.getsize(filename))
		sock.send(message.encode('utf-8'))
		response = sock.recv(1024)
		response = response.decode('utf-8')
		if response[:2] == 'OK':
			with open(filename, 'rb') as f:
				bytesToSend = f.read(1024)
				sock.send(bytesToSend)
				while bytesToSend != "":
					bytesToSend = f.read(1024)
					sock.send(bytesToSend)
	else:
		error = "ERR"
		sock.send(error.encode('utf-8') )
	sock.close()
def Main():
	host = '127.0.0.1'
	port = 80
	
	s = socket.socket()
	s.bind((host,port))
	
	s.listen(5)
	
	print('Server Started')
	
	while True:
		c, addr = s.accept()
		print( 'client connected ip:<'+str(addr)+'>')
		t = threading.Thread(target = RetrFile, args=("retrThread", c))
		t.start()
	s.close()

if __name__ == '__main__':
			Main()
