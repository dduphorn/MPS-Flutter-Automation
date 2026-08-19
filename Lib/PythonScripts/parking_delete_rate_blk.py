#!/usr/bin/python

"""
Delete the rate block with the provided id.
"""

import requests
import json
import sys

if len(sys.argv) > 1:
    rateblknum = int(sys.argv[1])
else:
    rateblknum = 99

DEVICES = [
    "localhost"
]

for device in DEVICES:
    req = {
        "jsonrpc": "2.0",
        "method": "delete_rate_block",
        "id": 1,
        "params": [rateblknum]
    }

    full_url = "http://%s:8080/rpc/v1/%s" % (device, "parking")
    response = requests.post(full_url, json.dumps(req))
    output = json.loads(response.content)
    print json.dumps(output["result"], indent=4, separators=(',', ': '))
