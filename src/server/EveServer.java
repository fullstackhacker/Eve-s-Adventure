package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EveServer implements Runnable {
	private ServerSocket servSocket;	
	
	public static void main(String[] args){ 
		try {
			new EveServer(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public EveServer(int port) throws IOException{ 
		this.servSocket = new ServerSocket(port); 
		System.out.println("Listening for connections on: " + port);
		new Thread(this).start();
	}
	
	@Override
	public void run(){ 
		while(true){ 
			try {
				Socket client = this.servSocket.accept();
				System.out.println("Got a connection: " + client.getPort());
				EveService es = new EveService(client);
				new Thread(es).start(); 
			} catch (IOException e) {
				System.out.println("Failed to accept client connection");
			}	
		}
	}
}
