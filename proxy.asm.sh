#!/bin/sh
PATH=$(pwd)
echo "======>$PATH"
java -javaagent:$PATH/premain-lib/premain.jar 