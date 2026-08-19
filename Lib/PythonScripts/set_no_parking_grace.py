#!/usr/bin/python

"""
Set No Parking grace period
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    ngrace = int(sys.argv[1])
else:
    ngrace = 60

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_NO_PARKING_GRACE_PERIOD", ngrace]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
