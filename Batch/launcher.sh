#!/bin/bash

# Script to run the Selenium hub with three nodes

SELENIUM_SERVER_JAR="../Lib/selenium-server-standalone-2.52.0.jar"
HUB_PATH="http://localhost:4444/grid/register"
LOG_FILE="../test-output/selenium.log"

declare -a PORTS=( "$@" )

# Kill active selenium processes
for pid in `ps -e | grep selenium | grep -v grep | awk '{print $1}'`
do
  kill $pid
done

# Check if logfile exists
if [ ! -f $LOG_FILE ]; then
  echo "Creating log file: $LOG_FILE"
  touch $LOG_FILE
fi

# Launch the Selenium Hub
java -jar ${SELENIUM_SERVER_JAR} -role hub >> $LOG_FILE 2>&1 &
PROCESSES[0]=$!

# Node Launcher
# First param is port
launch_node() {
  java -jar ${SELENIUM_SERVER_JAR} -role node -hub ${HUB_PATH} -port $1 -Dwebdriver.chrome.driver=chromedriver >> $LOG_FILE 2>&1 &
  PROCESSES[${#PROCESSES[@]}]=$!
}

# if ports === [], prompt to enter ports or lauch default ports
if [ ${#ports[@]} ];
then
  echo "You did not enter any ports"
fi

for PORT in "${PORTS[@]}"; do
  launch_node "$PORT"
done

# list processes through grep to find selenium

echo ""
echo "To kill all of these processes, use:"
echo "    kill ${PROCESSES[@]}"
echo ""
