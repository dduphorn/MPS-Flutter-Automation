#!/bin/bash
#
# Select and make card payment
#
/usr/local/bin/testautof_select_active_spot.py SPOT_1
/usr/local/bin/testautof_state_paying_card.py
sleep 1
/usr/local/bin/testautof_card_pay_maxtime.py
sleep 1
/usr/local/bin/testautof_accept_payment.py
sleep 1
/usr/local/bin/testautof_complete_card_payment.py
