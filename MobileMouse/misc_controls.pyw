__author__='Yugal'

import win32api
import win32con

#===========VOLUME===========================================================
def volume_up(): #id=a
        win32api.keybd_event(0xAF, 0,0,0)


def volume_down(): #id=b
        win32api.keybd_event(0xAE, 0,0,0)


def volume_mute(): #id=c
    win32api.keybd_event(0xAD, 0,0,0)


#============================================================================

def lbd():
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTDOWN,point[0],point[1],0,0)

def lbu():
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP,point[0],point[1],0,0)

def rbd():
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_RIGHTDOWN,point[0],point[1],0,0)

def rbu():
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_RIGHTUP,point[0],point[1],0,0)


#===========CLICK/Move=====================================================================


def click(): #id=d
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTDOWN,point[0],point[1],0,0)
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP,point[0],point[1],0,0)


def rightClick(): #id=e
    point = win32api.GetCursorPos()
    win32api.mouse_event(win32con.MOUSEEVENTF_RIGHTDOWN,point[0],point[1],0,0)
    win32api.mouse_event(win32con.MOUSEEVENTF_RIGHTUP,point[0],point[1],0,0)


def move(x,y): #id=f
   point = win32api.GetCursorPos()
   #win32api.mouse_event(win32con.MOUSEEVENTF_MOVE | win32con.MOUSEEVENTF_ABSOLUTE, point[0]+x*50,point[1]+y*50)
   win32api.SetCursorPos((point[0]+x,point[1]+y))

#=================ARROW KEYS=============================================

def up(): #id=g
    win32api.keybd_event(0x26, 0,0,0)

def down(): #id=h
    win32api.keybd_event(0x28, 0,0,0)

def left(): #id=i
    win32api.keybd_event(0x25, 0,0,0)

def right(): #id=j
    win32api.keybd_event(0x27, 0,0,0)

#=========================================================================









