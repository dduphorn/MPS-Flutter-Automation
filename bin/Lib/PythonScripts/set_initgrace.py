#!/usr/bin/python

"""
Get listing of all settings.
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    igrace = int(sys.argv[1])
else:
    igrace = 60

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_INIT_GRACE_PERIOD", igrace]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
