# MobileMouse

****Note: it's a part of my AI assistant VINI, and some parts(files) of this app have been removed. The main activity belonged to the functionality of VINI app, of which the MobileMouse is a subset. Those files have been removed.****


A aimple android app that acts as a remote mouse for your PC (windows only). 
This project consists of a backend server made in python and it uses sockets to connect to the android app. Security vulnerabilities can be addressed and dealt with very easily(for example using SSL security for sockets).

required libraries in python:
    $pip install win32con
    $pip install win32api
run:

$python MobileMouse

Windows server side exe file(installable) here:
https://drive.google.com/open?id=0B8Id8pGp8lAGWmJkVHNnejRuYVE
Android app Here:
https://drive.google.com/open?id=0B8Id8pGp8lAGVXMtWTZrSkRPWUE


One more Step:
  You have to set the IP address of your PC in the settings of the MobileMouse android app.
  To do that, go to cmd, type command:
     $ipconfig
     
The ip address is the value of IPv4 address key.
