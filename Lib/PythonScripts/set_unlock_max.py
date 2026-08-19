#!/usr/bin/python

"""
Set number of times a parking session can be unlocked
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    unlockmax = int(sys.argv[1])
else:
    unlockmax = 10
DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_UNLOCK_MAX", unlockmax]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
