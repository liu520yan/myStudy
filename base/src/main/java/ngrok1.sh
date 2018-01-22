#!/bin/bash
set -x

TRUE=true
echo "server_addr: $NGROK_DOMAIN:$SERVER_PROXY_PORT" >ngrok-config
echo "inspect_addr: 0.0.0.0:4040" >>ngrok-config
echo "tunnels:" >>ngrok-config

IFS=","
arr=($SUBDOMAIN)
for s in ${arr[@]}
do
echo "  $s:" >>ngrok-config
echo "    SUBDOMAIN: $s" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      http: 80" >>ngrok-config
done

echo ${SUBDOMAIN/,/ }   # 替换,

if [ "$OPRNSHH" = "$TRUE" ];then
echo "  ssh:" >>ngrok-config
echo "    remote_port: 6666:" >>ngrok-config
echo "    proto:6000" >>ngrok-config
echo "      tcp: 22" >>ngrok-config

SUBDOMAIN=$SUBDOMAIN" ssh"
fi

echo $SUBDOMAIN
exec ./bin/ngrok -config=ngrok-config -log=stdout start ssh $SUBDOMAIN
