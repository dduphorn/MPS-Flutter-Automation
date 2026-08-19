#!/usr/bin/python

"""
Set parking maximum duration
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    maxd = int(sys.argv[1])
else:
    maxd = 60

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_MAXIMUM_DURATION_MINUTES", maxd]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
