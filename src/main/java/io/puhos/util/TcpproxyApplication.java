package io.puhos.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcpproxyApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TcpproxyApplication.class, args);
		
		if (args.length != 3 && args.length != 4) {
            System.err.println("Usage: java TcpTunnel listenport tunnelhost tunnelport [encoding]");
            System.exit(1);
        }
        int listenport = Integer.parseInt(args[0]);
        String tunnelhost = args[1];
        int tunnelport = Integer.parseInt(args[2]);
        String enc;
        if (args.length == 4) {
            enc = args[3];
        } else {
            enc = "8859_1";
        }
        System.out.println("TcpTunnel: ready to rock and roll on port " + listenport);
        ServerSocket ss = new ServerSocket(listenport);
        while (true) {
            // accept the connection from my client
            Socket sc = ss.accept();

            // connect to the thing I'm tunnelling for
            Socket st = new Socket(tunnelhost, tunnelport);
            System.out.println("TcpTunnel: tunnelling port " + listenport + " to port " + tunnelport + " on host " + tunnelhost);

            // relay the stuff thru
            new Relay(sc.getInputStream(), st.getOutputStream(), System.out, enc).start();
            new Relay(st.getInputStream(), sc.getOutputStream(), System.out, enc).start();
            // that's it .. they're off; now I go back to my stuff.
        }
  
	}
}
