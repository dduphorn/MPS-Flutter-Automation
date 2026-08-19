#!/usr/bin/python

"""
Simulated screen touch
"""

import requests
import json

device = "localhost"
full_url = "http://%s:8080/rpc/v1/%s" % (device, "system")
response = requests.post(full_url, '{"params": ["\
\
import sys, string, os, time\\n\
ui.interaction.on_touch_screen()\\n\
\
"], "jsonrpc": "2.0", "method": "run_python", "id": 1}')
output = json.loads(response.content)
print json.dumps(output["result"], indent=4, separators=(',', ': '))

