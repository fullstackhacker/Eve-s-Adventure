package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import models.campaign.World;
import controllers.Save;

/**
 * The client to upload and download files to the server
 */
public class EveClient{
	/**
	 * The server connection
	 */
	private Socket server; 
	/**
	 * Reads from the server
	 */
	private DataInputStream in; 
	/**
	 * Writes to the server
	 */
	private DataOutputStream out; 
	
	/**
	 * Constructor Class for the client
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public EveClient() throws UnknownHostException, IOException{ 
		this.server = null;
		this.in = null; 
		this.out = null;	
	}
	
	private void connect() throws UnknownHostException, IOException{ 
		this.server = new Socket("localhost", 5000); 
		this.in = new DataInputStream(this.server.getInputStream()); 
		this.out = new DataOutputStream(this.server.getOutputStream());
	}
	
	private void disconnect() throws IOException{ 
		this.out.close();
		this.in.close(); 
		this.server.close();
	}
	
	public void uploadWorld(World world) throws UnknownHostException, IOException{
		/* connect to the server */
		this.connect();
		
		/* read from the server */
		String fS = ""; 
		fS = this.in.readUTF(); 
		if(!fS.equals("?")){
			throw new UnknownHostException("Invalid Host"); 
		}
		
		/* write to the server */
		String tS = "uploadWorld"; 
		this.out.writeUTF(tS); 
		
		/* read the ready from server */
		fS = this.in.readUTF();
		if(!fS.equals("!")) throw new UnknownHostException("Invalid Host"); 
		
		/* Write file to server */
		ObjectOutputStream oos = new ObjectOutputStream(this.out); 
		oos.writeObject(world); 
		oos.close();
		
		/* disconnect from the server */
		this.disconnect(); 
	}
	
	public World downloadWorld(String worldName) throws UnknownHostException, IOException{
		/* connect to the server */
		this.connect();
		
		/* read from the server */
		String fS = ""; 
		fS = this.in.readUTF(); 
		if(!fS.equals("?")) throw new UnknownHostException("Invalid Host"); 
		
		/* write to the server */
		String tS = "downloadWorld"; 
		this.out.writeUTF(tS); 
		
		/* read the ready from server */
		fS = this.in.readUTF(); 
		if(!fS.equals("!")) throw new UnknownHostException("Invalid Host"); 
		
		/* write world name to server */
		this.out.writeUTF(worldName); 
		
		/* read in the world object */
		ObjectInputStream ois = new ObjectInputStream(this.in);
		World world = null;
		try {
			world = (World) ois.readObject();
		} catch (ClassNotFoundException e) {
			//class not found --
			return null; //
		} 
		ois.close(); 
		
		/* edit name for differential ability */
		world.setName(world.getName() + "_clientcopy"); //remove once server is using separate data folder
		
		/* disconnect from the server */
		this.disconnect(); 
		
		/* save the world */
		Save.saveWorld(world);  
		
		/* return the world */
		return world; 
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<String> getWorlds() throws UnknownHostException, IOException{ 
		/* connect to the server */
		this.connect(); 
		
		/* read from the server */
		String fS = ""; 
		fS = this.in.readUTF(); 
		if(!fS.equals("?")) throw new UnknownHostException(); 
		
		/* write to server */
		String tS = "listWorlds"; 
		this.out.writeUTF(tS); 
		
		/* read object from server */
		ArrayList<String> worldsList = null; 
		ObjectInputStream ois = new ObjectInputStream(this.in); 
		try{ 
			worldsList = (ArrayList<String>) ois.readObject();
		}
		catch(ClassNotFoundException e){ 
			//
			return null; 
		}
		ois.close(); 
		
		/* return the list of worlds */
		return worldsList.iterator(); 
	}
	
	/* Main method just to Test */
	public static void main(String[] args){ 
		EveClient ec = null; 
		try {
			ec = new EveClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		World world = new World("Mushs_World", 2, 2);
		Save.saveWorld(world); 
		try {
			ec.uploadWorld(world);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			ec = new EveClient();
		}
		catch(Exception e){
			
		}
		try{
			ec.downloadWorld("Mushs_World");
		}
		catch(Exception e){ 
			System.out.println("there was an error"); 
			return;
		}
		
		
		Iterator<String> worldsList = null; 
		try{ 
			worldsList = ec.getWorlds();
		}
		catch(Exception e){ 
			System.out.println("ERrorr"); 
			return ;
		}
		
		while(worldsList.hasNext()){ 
			System.out.println(worldsList.next());
		}
	}
		
}
