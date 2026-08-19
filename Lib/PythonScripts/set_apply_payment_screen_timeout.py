#!/usr/bin/python

"""
Set timeout value for apply payment screen
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    tov = int(sys.argv[1])
else:
    tov = 11

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_ACCEPT_COIN_NO_SPOT_SCREEN_TIMEOUT", tov]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
