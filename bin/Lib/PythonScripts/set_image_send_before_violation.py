#!/usr/bin/python

"""
Number of seconds before a violation the parkingsession starts sending images.
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    b4v = int(sys.argv[1])
else:
    b4v = 120

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "set_setting",
        "id": 1,
        "params": ["PARKING_IMAGE_SEND_BEFORE_VIOLATION", b4v]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "settings")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
