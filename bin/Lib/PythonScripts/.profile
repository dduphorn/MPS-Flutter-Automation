# Miscellaneous short cuts
alias cei='cat /etc/network/interfaces'
alias cer='cat /var/lib/sentry/resolv.conf'
alias e0="cp /home/root/apcc.cfg.e0 /home/root/apcc.cfg"
alias e4="cp /home/root/apcc.cfg.e4 /home/root/apcc.cfg"
alias e00="cp /home/root/apcc.cfg.e0e0 /home/root/apcc.cfg"
alias e04="cp /home/root/apcc.cfg.e0e4 /home/root/apcc.cfg"
alias e40="cp /home/root/apcc.cfg.e4e0 /home/root/apcc.cfg"
alias e44="cp /home/root/apcc.cfg.e4e4 /home/root/apcc.cfg"
alias d2ts="date +%s"
alias d2tz='env TZ="America/Chicago" date -d "@`date +%s`"'
alias gapcc='ps | grep apcc'
alias gbc1='grep "Buffered credit error: 1" /var/log/Xsession.log | wc'
alias gbm='grep banner_apcc /var/lib/sentry/messages | tail -n 40'
alias gbv='grep "Banner daemon version" /var/lib/sentry/messages'
alias gfp='grep "found plate" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gfpf='grep "found plate" '
alias gfn='settings_get_settings.py | grep -A 4 FRIEND'
alias gpu='grep -E "\- BOOT|puck" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gpuf='grep -E "\- BOOT|puck" '
alias gs4='settings_get_settings.py | grep -A 4 '
alias gs6='settings_get_settings.py | grep -A 6 '
alias gs8='settings_get_settings.py | grep -A 8 '
alias gs10='settings_get_settings.py | grep -A 10 '
alias gs12='settings_get_settings.py | grep -A 12 '
alias gta='cat /var/log/Xsession.log | grep "path /alert/"'
alias gtb='grep -A 1 Traceback /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gtbb='grep -E "\- BOOT|Traceback" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gtbf='grep -A 1 Traceback '
alias gtbbf='grep -E "\- BOOT|Traceback" '
alias gts='grep -A 1 STACK /var/log/Xsession.log | sed -e "s/#~> //g"'
alias guh='grep -E "\- BOOT|Unhandled" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias guhf='grep -E "\- BOOT|Unhandled" '
alias gvd='grep -E "vdetect|VIOLATION on" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gvdf='grep -E "vdetect|VIOLATION on" '
alias gvdt='grep -E "vdetect|VIOLATION on" /var/log/Xsession.log | sed -e "s/#~> //g" | tail'
alias gve='grep -E "Vehicle Arrival Event|VIOLATION on|Vehicle Exit Event" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gvef='grep -E "Vehicle Arrival Event|VIOLATION on|Vehicle Exit Event" '
alias gvmdf='grep -E "puck|vmd_buffer|VIOLATION on" '
alias gvo='grep "VIOLATION on" /var/log/Xsession.log | sed -e "s/#~> //g"'
alias gvof='grep "VIOLATION on" '
alias ifa='ifconfig -a'
alias if0='ifconfig eth0'
alias llds='ls -alF /dev/sentry/'
alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'
alias lth="ls -lt | head"
alias mps="cd /opt/mps"
alias pss='ps | grep selk'
alias rb='strings /var/lib/sentry/repo/main/rates.pdo ; echo "" ; md5sum /var/lib/sentry/repo/main/rates.pdo'
alias rb5='md5sum /var/lib/sentry/repo/main/rates.pdo'
alias rbl='strings /var/lib/sentry/repo/main/rates.pdo | grep "^{" | sed "s|}}q|}}|" | while read line; do echo $line |  python -mjson.tool; done'
alias rbt='strings /var/lib/sentry/repo/main/rates.pdo | grep "^{" | sed "s|}}q|}}|" | while read line; do echo $line |  python -mjson.tool; done | grep -E "type|time"'
alias repo="cd /var/lib/sentry/repo/main"
alias rps='nohup /usr/bin/python /home/root/apcc_server.py > /dev/null &'
alias sapr="/etc/init.d/xserver-nodm restart"
alias sgs="/usr/local/bin/sys_get_sw_version.py"
alias sgss="/usr/local/bin/sys_get_screen_snapshot.sh"
alias sum="sgs ; gtb ; guh ; gbc ; gts"
alias summ="sgs ; gtb ; guh ; gbc ; gts ; gvo ; gta ; tx"
alias ta="tail /var/lib/sentry/apcc_server_log.txt"
alias tfa="tail -f /var/lib/sentry/apcc_server_log.txt"
alias tl="tail /var/log/log.txt"
alias tfl="tail -f /var/log/log.txt"
alias tm="tail /var/lib/sentry/messages"
alias tfm="tail -f /var/lib/sentry/messages"
alias tfbm="tail -f /var/lib/sentry/messages | grep banner_apcc"
alias tx='tail /var/log/Xsession.log | sed -e "s/#~> //g"'
alias tfx='tail -f /var/log/Xsession.log | sed -e "s/#~> //g"'
alias udev="cd /etc/udev/rules.d"
alias ulb="cd /usr/local/bin"
alias up="cd .."
alias up2="cd ../.."
alias vix="vi /var/log/Xsession.log"
alias vim="vi /var/lib/sentry/messages"
alias vl="cd /var/log"
alias vlx="less /var/log/Xsession.log"
alias vls="cd /var/lib/sentry"
alias vlm="less /var/lib/sentry/messages"
alias vll="less /var/log/log.txt"
ts2dz() {
	env TZ="America/Chicago" date -d "@$1"
}
ts2du() {
	echo $1 | awk '{ print strftime("%c", $0); }'
}
ts2d() {
	date -d "@$1"
}
llu0() {
	udevadm info -a -p $(udevadm info -q path -n /dev/ttyUSB0) | head -n 32 | grep -E "looking at device|interface"
}
llu1() {
	udevadm info -a -p $(udevadm info -q path -n /dev/ttyUSB1) | head -n 32 | grep -E "looking at device|interface"
}
llu2() {
	udevadm info -a -p $(udevadm info -q path -n /dev/ttyUSB2) | head -n 32 | grep -E "looking at device|interface"
}
llv0() {
	udevadm info -a -p $(udevadm info -q path -n /dev/video0) | head -n 32 | grep -E "looking at device|interface"
}
llv1() {
	udevadm info -a -p $(udevadm info -q path -n /dev/video1) | head -n 32 | grep -E "looking at device|interface"
}
