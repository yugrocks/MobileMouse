__author__ = 'Yugal'

from misc_controls import *
from time import sleep
import VINI_server

vs=VINI_server.viniServer()

def enable_server_response():
        vs.run_server()
        start_checking_for_response()

def start_checking_for_response():
    if vs.isConnected():
        a=vs.wait_for_response()
        if a==None:
            return
        if a=="__0ms__":
            while True:
                sleep(0.0005)
                try:
                   msg2=vs.getConn().recv(1024)
                   if msg2.decode()=="__sbk__400":
                        break
                   try:
                      j=getInt(msg2.decode())
                      for k in j:
                          move(k[0]*3,k[1]*2)
                   except:
                      pass
                   if msg2.decode()=="Enter":
                           Enter_key()
                   elif msg2.decode()=="Backspace":
                           backspace()
                   elif "added" in msg2.decode():
                           handleKey(msg2.decode()[-1])
                   elif msg2.decode()=="lbd":
                           lbd()
                   elif msg2.decode()=="lbu":
                           lbu()
                   elif msg2.decode()=="d":
                           click()
                   elif msg2.decode()=="rbd":
                           rbd()
                   elif msg2.decode()=="rbu":
                           rbu()
                   elif msg2.decode()=="a":
                           volume_up()
                   elif msg2.decode()=="b":
                           volume_down()
                   elif msg2.decode()=="c":
                           volume_mute()
                   elif msg2.decode()=="g":
                           up()
                   elif msg2.decode()=="h":
                           down()
                   elif msg2.decode()=="i":
                           left()
                   elif msg2.decode()=="j":
                           right()
                except:
                   return


def getInt(string):
        c=[];d=[]
        spl=string.split(",")
        n=len(spl)
        for k in range(0,n,2):
            try:
              d.append((int(float(spl[k])),int(float(spl[k+1]))))
            except:
              continue
        return d


def processResponse(a):
    if not vs.isConnected():
        return
    start_checking_for_response()


def start():
    while True:
        enable_server_response()


start()
