#!/bin/bash
set -x

echo "server_addr: $NGROK_DOMAIN:$SERVER_PROXY_PORT" >ngrok-config
echo "tunnels:" >>ngrok-config
echo "  oauth2:" >>ngrok-config
echo "    subdomain: oauth2" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      http: 80" >>ngrok-config
echo "  service:" >>ngrok-config
echo "    subdomain: service" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      http: 80" >>ngrok-config
echo "  ssh1:" >>ngrok-config
echo "    remote_port: 52221" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      tcp: 22" >>ngrok-config
echo "  ssh2:" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      tcp: 22" >>ngrok-config
echo "  eureka:" >>ngrok-config
echo "    subdomain: eureka" >>ngrok-config
echo "    proto:" >>ngrok-config
echo "      http: 80" >>ngrok-config


server_addr: "ngrok.xiaomiqiu.cn:4443"
trust_host_root_certs: false
inspect_addr: 0.0.0.0:4040
tunnels:
  oauth2:
    subdomain: oauth2
    proto:
      http: 80
  eureka:
    subdomain: eureka
    proto:
      http: 80
  ssh1:
    remote_port: 52221
    proto:
      tcp: 22


exec ./bin/ngrok -config=ngrok-config -log=stdout start service oauth2 ssh1 ssh2 eureka