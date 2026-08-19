#!/usr/bin/python
"""
Get parking session information
"""

import requests
import json

device = "localhost"

parts = []
parts.append("\
import sys, datetime\n\
parker_type = \"normal\"\n\
start_dt = datetime.datetime.now()\n\
time_already_purchased = 0\n\
cost, granted, srbs_used = ui.get_rate_schedule().get_next_rate_increment(parker_type, start_dt, time_already_purchased)\n\
sys.stdout.write(\"NextRateCost,%s|granted,%s|srbs_used,%s\" % (cost,granted,srbs_used))\n\
for spot_id, spot in parking_controller._parking_spots.iteritems():\n\
    session = spot.get_session()\n\
    sys.stdout.write(\"\\n%s\" % spot_id)\n\
    sys.stdout.write(\"|MaxTime,%s\" % session.get_maxtime_minutes())\n\
    sys.stdout.write(\"|MaxRemaining,%s\" % session.get_maxtime_minutes_remaining())\n\
    sys.stdout.write(\"|ValidTimePurchased,%s\" % session.get_valid_time_purchased())\n\
    sys.stdout.write(\"|ValidTimeRemaining,%s\" % session.get_valid_time_remaining())\n\
    sys.stdout.write(\"|Violation,%s\" % session.is_violation())\n\
    sys.stdout.write(\"|Unlocked,%s\" % session.is_unlocked())\n\
    sys.stdout.write(\"|Free,%s\" % session.is_free_parking_now())\n\
    sys.stdout.write(\"|No,%s\" % session.is_no_parking_now())\n\
    sys.stdout.write(\"|Begun,%s\" % session.has_begun())\n\
    sys.stdout.write(\"|ParkTime,%s\" % session.get_park_timestamp())\n\
    sys.stdout.write(\"|ExitTime,%s\" % session.get_exit_timestamp())\n\
")

pyprog = ''.join(parts)

req = {
    "jsonrpc": "2.0",
    "method": "run_python",
    "id": 1,
}
req["params"] = [pyprog]

full_url = "http://%s:8080/rpc/v1/%s" % (device, "system")
response = requests.post(full_url, json.dumps(req))
#print response.status_code == requests.codes.ok
#print response.text
output = json.loads(response.content)
s = json.dumps(output["result"], indent=4, separators=(',', ': '), default=str).decode('string_escape')
print s.strip("\"")

