# TCPproxy
Spring boot tcp proxy based on Apache's TCPMon 1.0 TcpTunnel

## Usage

Usage: java TcpTunnel listenport tunnelhost tunnelport [encoding]

## To run in OpenShit

JAVA_ARGS=listenport tunnelhost tunnelport [encoding]

## Port forwarding

oc port-forward local-port:listenport
