#!/usr/bin/python

"""
Force Simulated Park (Spot1 = 1, Spot2 = 2)
"""

import requests
import json

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "force_simulated_park",
        "id": 1,
        "params": [1]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "vehicle_detector")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
