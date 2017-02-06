package com.winderton.messenger.server;

public class ServerMain {

	private int port;
	private Server server;
	
	public ServerMain(int port) {
		this.port = port;
		new Server(port);
	}
	
	public static void main(String[] args) {
		int port;
		if(args.length != 1) {
			System.out.println("Usage: java -jar program.jar [port]");
			System.exit(1);
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}
	
}
