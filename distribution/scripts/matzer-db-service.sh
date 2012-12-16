#!/bin/bash

JAVA_HOME=/usr/lib/jvm/java-7-oracle/bin
HOME=/opt/homer
PROGRAM_DIR=wh-database-service
PID=$HOME/$PROGRAM_DIR/pid
SCRIPT_NAME=wh-database-service.sh

PROGRAM_NAME=$PROGRAM_DIR

JAVA=$JAVA_HOME/java
JAVA_OPTS=-Dfile.encoding=UTF-8
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8099" 
LIB_DIR=lib

CLASSPATH=config
for file in $(find $LIB_DIR)
do
  CLASSPATH=$CLASSPATH:$file
done

OPTION=$1

if [ "$OPTION" = "start" ]; then
  if [[ `ps -eaf | grep "D$PROGRAM_NAME" | grep -v grep | wc -l` -gt 0 ]]; then
    echo "`date` - process $PROGRAM_NAME already exists"
    exit 1
  fi

  cd $HOME/$PROGRAM_DIR
  $JAVA -D$PROGRAM_NAME $JAVA_OPTS -cp $CLASSPATH com.matzer.db.api.Main &
  CURRPID="$!"
  echo $CURRPID > $PID
  echo "$HOME/$PROGRAM_DIR started - process number $CURRPID"
  sleep 1
  if [[ `ps -eaf | grep "D$PROGRAM_NAME" | grep -v grep | wc -l` -le 0 ]]; then
    echo "$PROGRAM_NAME with process $CURRPID doesn't work"
    echo "(it didn't launch or it terminated very fast)"
    rm $PID
    exit 1
  fi
elif [ "$OPTION" = "kill" ]; then
  if [[ `ps -eaf | grep "D$PROGRAM_NAME" | grep -v grep | wc -l` -gt 0 ]]; then
    echo "`date` - stopping $PROGRAM_NAME"
    kill -9 `cat $PID`
  fi
elif [ "$OPTION" = "stop" ]; then
  if [[ `ps -eaf | grep "D$PROGRAM_NAME" | grep -v grep | wc -l` -gt 0 ]]; then
    echo "`date` - stopping $PROGRAM_NAME"
    kill `cat $PID`
  fi
elif [ "$OPTION" = "restart" ]; then
  $HOME/$PROGRAM_DIR/$SCRIPT_NAME kill
  $HOME/$PROGRAM_DIR/$SCRIPT_NAME start
else
  echo 'usage : start|stop|kill|restart'
fi
