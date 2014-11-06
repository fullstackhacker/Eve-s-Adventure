package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import models.campaign.World;
import controllers.Load;
import controllers.Save;

/**
 * Handles the client connection, figures out what the client is trying to do
 * 
 * 
 * @author Mush
 *
 */
public class EveService implements Runnable{
	
	/**
	 * The client connection
	 */
	private Socket client; 
	/**
	 * Input stream to read from the client
	 */
	private DataInputStream in; 
	/**
	 * Output stream to write to the client
	 */
	private DataOutputStream out;
	
	public EveService(Socket client) throws IOException{ 
		this.client = client; 
		this.in = new DataInputStream(this.client.getInputStream());
		this.out = new DataOutputStream(this.client.getOutputStream()); 
	}
	
	@Override
	public void run(){
		/* Write to the client */
		String tC = "?"; 
		try {
			this.out.writeUTF(tC);
		} catch (IOException e) {
			System.out.println("Failed to write to the client");
		}
		
		/* Read from the client */
		String fC = ""; 
		try{
			fC = this.in.readUTF();
			System.out.println(fC); 
		}
		catch(Exception e){ 
			System.out.println("Unable to read from the client"); 
		}
		
		switch(fC){ 
		case "uploadWorld": 
			uploadWorld(); 
			break;
		case "downloadWorld":
			downloadWorld();
			break;
		}
	}
	
	public void uploadWorld(){ 
		/* Tell client ready for file */
		try{ 
			String tC = "!"; 
			this.out.writeUTF(tC);
		}
		catch(Exception e){ 
			System.out.println("Unable to write to client"); 
		}
		
		/* Set up the object reader */
		ObjectInputStream ois = null; 
		try{ 
			ois = new ObjectInputStream(this.in);
		}
		catch(Exception e){
			//unable to set up the reader
		}
		
		/* Read in the object */
		try{
			World w = (World) ois.readObject();
			System.out.println("Read in the world object: " + w.getName()); 
			w.setName(w.getName() + "_servercopy");
			Save.saveWorld(w);
		}
		catch(Exception e){ 
			//unable to read world object
		}
	}
	
	public void downloadWorld(){
		/* tell client received download notion respond with world name */
		try{
			String tC = "!";
			this.out.writeUTF(tC);
		}
		catch(Exception e){
			System.out.println("Unable to write to client"); 
		}
		
		/* Read world name from the client */
		String worldName = ""; 
		try{
			worldName = this.in.readUTF();
			System.out.println(worldName); 
		}
		catch(Exception e){ 
			System.out.println("Unable to read from the client"); 
		}
		
		/* load world object */
		World world = Load.loadWorld(worldName + "_servercopy"); 
		
		/* respond with object */
		ObjectOutputStream oos = null;
		
		try{ 
			oos = new ObjectOutputStream(this.out); 
			oos.writeObject(world); 
			oos.close();
		}
		catch(Exception e){ 
			System.out.println("Failed to send world to client"); 
		}
		
	
		
	}
}
