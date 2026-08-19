#!/usr/bin/bash

# Script to run the Selenium hub with three nodes

SELENIUM_SERVER_JAR="selenium-server-standalone-2.50.1.jar"
SELENIUM_JAR="selenium-server-standalone-2.50.1.jar"
HUB_PATH="http://localhost:4444/grid/register"
LOG_FILE="selenium-log.txt"
DEFAULT_NODES=( "5555" "6666" "7777" )

# ---------------
# Create options
# (1) -h to output usage
# (2) command to kill all selenium processes
# ---------------

# http://unix.stackexchange.com/questions/149419/how-to-check-whether-a-particular-port-is-open-on-a-machine-from-a-shell-script


declare -a PORTS=( "$@" )

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
  java -jar ${SELENIUM_JAR} -role node -hub ${HUB_PATH} -port $1 -Dwebdriver.chrome.driver=chromedriver >> $LOG_FILE 2>&1 &
  PROCESSES[${#PROCESSES[@]}]=$!
}

# if ports === [], prompt to enter ports or lauch default ports
# if [ ${#ports[@]} ]

# for PORT in "${PORTS[@]}"; do
#   launch_node "$PORT"
# done

# Launch Nodes
launch_node 5555
launch_node 6666
launch_node 7777

# list processes through grep to find selenium

echo ""
echo "To kill all of these processes, use:"
echo "    kill ${PROCESSES[@]}"
echo ""
