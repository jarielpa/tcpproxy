# TCPproxy
Spring boot tcp proxy based on Apache's TCPMon 1.0 TcpTunnel

## Usage

Usage: java -jar tcpproxy.jar listenport tunnelhost tunnelport [encoding]

## To run in OpenShit (OCP 3.4)

   oc new-app . registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift:latest --allow-missing-images

	mvn clean install
	
	mkdir build/deployments
	
	cp target/*.jar build/deployments
	
	oc start-build tcpproxy --from-dir=build --follow
	

Add environment variable to deployment configuratio:

	JAVA_ARGS=listenport tunnelhost tunnelport [encoding]

## Port forwarding

	oc port-forward local-port:listenport
