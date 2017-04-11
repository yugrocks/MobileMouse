__author__='Yugal'

from socket import SOL_SOCKET,SO_REUSEADDR,AF_INET,SOCK_STREAM,socket
from time import strftime,sleep
from threading import Thread
import os

class viniServer:
    conn = None
    s=None

    def __init__(self):
        self.s=socket(AF_INET,SOCK_STREAM)
        self.s.setsockopt(SOL_SOCKET,SO_REUSEADDR, 1)
        self.s.bind(("", 9999))     # Let's Bind to port
        self.s.listen(0)  # Specify number of connections//0=1 :P


    @staticmethod
    def send(conn,message):
        try:
            message+="\n"
            conn.send(message.encode())
        except:
            pass

    @staticmethod
    def refine(string):
        e=""
        for i in range(len(string)-2):
            k=i+2
            e+=string[k]
        return e

    def getConn(self):
        return self.conn

    def run_server(self):
        if True:
            print("Waiting for connection")
            self.conn, addr = self.s.accept()
            print("Now connected to ", addr)
            Thread(target=self.startConnCheck).start()
            return True
        else:
            return False

    def wait_for_response(self):
        try:
            msg = self.conn.recv(1024)
            if msg == b'':
                self.conn.close()
                return None
            a=msg.decode('utf-8')
            return a
        except:
            self.conn.close()
            self.conn=None
            return None

    def isConnected(self):
        if "closed" in str(self.conn):
            return False
        else:
            return True
        
    def startConnCheck(self):
      k=0
      while True:
        sleep(1)
        if self.isConnected():
            self.send(self.conn,"__ccheck_404")
        else:
          if self.isConnected():
            self.conn.close()
            self.conn=None
          break
    


            

