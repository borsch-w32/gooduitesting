#!/bin/sh
nohup java -jar selenium-server-standalone-2.49.0.jar -role hub -browser browserName=firefox,version=43.0,platform=LINUX > /dev/null 2>&1 &
