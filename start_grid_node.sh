#!/bin/sh
java -jar selenium-server-standalone-2.48.2.jar -role node -hub http://192.168.10.4:4444/grid/register -browser browserName=firefox,version=43.0,platform=LINUX
