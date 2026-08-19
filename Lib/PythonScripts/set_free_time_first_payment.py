#!/usr/bin/python

"""
Set number of free time minutes to be added to valid time purchased after first payment coin or card is received
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    freetime = int(sys.argv[1])
else:
    freetime = 0

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_FREE_TIME_ON_FIRST_PAYMENT", freetime]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
