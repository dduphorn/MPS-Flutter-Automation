#!/usr/bin/python

"""
Select active spot
"""

import requests
import json

device = "localhost"
full_url = "http://%s:8080/rpc/v1/%s" % (device, "system")
response = requests.post(full_url, '{"params": ["\
\
import sys, string, os, time\\n\
ui.interaction.set_active_spot(\\"SPOT_1\\")\\n\
ui.interaction.set_state(\\"UI_STATE_PAYING_COIN\\", \\"initial state\\")\\n\
coin_vault._sim_handle_buffered_credit_response(25)\\n\
\
"], "jsonrpc": "2.0", "method": "run_python", "id": 1}')
output = json.loads(response.content)
print json.dumps(output["result"], indent=4, separators=(',', ': '))

