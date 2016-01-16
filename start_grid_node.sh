#!/bin/sh
java -jar /home/cqi/IdeaProjects/gooduitesting/src/main/java/ru/mail/libs/selenium-server-standalone-2.49.0.jar -role node -hub http://192.168.10.4:4444/grid/register -browser browserName=firefox,version=43.0,platform=LINUX
