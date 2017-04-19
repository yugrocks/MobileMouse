__author__='Yugal'

import win32api
import win32con

without_shift={'0':0x30,  '1':0x31, '2':0x32, '3':0x33,'4':0x34,'5':0x35,
           '6':0x36,'7':0x37, '8':0x38,'9':0x39,'a':0x41,'b':0x42,'c':0x43,
           'd':0x44,'e':0x45,'f':0x46,'g':0x47,'h':0x48,'i':0x49,'j':0x4A,
           'k':0x4B,'l':0x4C,'m':0x4D,'n':0x4E,'o':0x4F,'p':0x50,'q':0x51,'r':0x52,
           's':0x53,'t':0x54,
           'u':0x55, 'v':0x56,'w':0x57,'x':0x58,'y':0x59,'z':0x5A,'/':0xBF,
           ',':0xBC,
           '-':0xBD,
           '.':0xBE,
           '/':0xBF,
           ';':0xBA,
           '[':0xDB,
           '\\':0xDC,
           ']':0xDD,
           "'":0xDE,
           ' ':0x20,
           '=':0xBB,
               }

with_shift={')':0x30,  '!':0x31, '@':0x32, '#':0x33,'$':0x34,'%':0x35,
           '^':0x36,'&':0x37, '*':0x38,'(':0x39,'A':0x41,'B':0x42,'C':0x43,
           'D':0x44,'E':0x45,'F':0x46,'G':0x47,'H':0x48,'I':0x49,'J':0x4A,
           'K':0x4B,'L':0x4C,'M':0x4D,'N':0x4E,'O':0x4F,'P':0x50,'Q':0x51,'R':0x52,
           'S':0x53,'T':0x54,
           'U':0x55, 'V':0x56,'W':0x57,'X':0x58,'Y':0x59,'Z':0x5A,'?':0xBF,
           '<':0xBC,
           '_':0xBD,
           '>':0xBE,
           '?':0xBF,
           ':':0xBA,
           '{':0xDB,
           '|':0xDC,
           '}':0xDD,
           '"':0xDE,
           ' ':0x20,
           '+':0xBB,
               }


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

def backspace():
        win32api.keybd_event(0x08,0,0,0)


def press(keycode):
        win32api.keybd_event(keycode,0,0,0)

def pressWithShift(keycode):
        win32api.keybd_event(160,0,1,0)
        press(keycode)
        win32api.keybd_event(160, 0, win32con.KEYEVENTF_EXTENDEDKEY  | win32con.KEYEVENTF_KEYUP, 0)


def handleKey(key):
        if key in without_shift:
                press(without_shift[key])
        if key in with_shift:
                pressWithShift(with_shift[key])
def Enter_key():
         win32api.keybd_event(0x0D,0,0,0)









