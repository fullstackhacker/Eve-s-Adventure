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
	
	public void uploadWorld(World world) throws UnknownHostException, IOException{
		/* connect to the server */
		this.connect();
		
		/* read from the server */
		try{ 
			String fS = ""; 
			fS = this.in.readUTF();
			System.out.println(fS);
		}
		catch(IOException e){ 
			System.out.println(e.getMessage());
			throw new IOException("Failed to read from the server"); 
		}
		
		/* write to the server */
		try{ 
			String tS = "uploadWorld"; 
			this.out.writeUTF(tS); 
		}
		catch(IOException e){ 
			System.out.println("Unable to write to server");
		}
		
		/* read the ready from server */
		try{ 
			String fS = ""; 
			fS = this.in.readUTF();
			System.out.println(fS); 
		}
		catch(IOException e){ 
			System.out.println("failed to read from the server"); 
		}
		
		/* Write file to server */
		ObjectOutputStream oos;
		try{ 
			oos = new ObjectOutputStream(this.out); 
			oos.writeObject(world); 
			oos.close(); 
		}
		catch(IOException e){ 
			
		}
	}
	
	public void downloadWorld(String worldName){
		/* read from the server */
		try{ 
			String fS = ""; 
			fS = this.in.readUTF();
			System.out.println(fS);
		}
		catch(Exception e){ 
			System.out.println("failed to read from the server"); 
		}
		
		/* write to the server */
		try{ 
			String tS = "downloadWorld"; 
			this.out.writeUTF(tS); 
		}
		catch(Exception e){ 
			System.out.println("Unable to write to server");
		}
		
		/* read the ready from server */
		try{ 
			String fS = ""; 
			fS = this.in.readUTF();
			System.out.println(fS); 
		}
		catch(Exception e){ 
			System.out.println("failed to read from the server"); 
		}
		
		/* write world name to server */
		try{ 
			this.out.writeUTF(worldName);
		}
		catch(Exception e){
			System.out.println("Unable to write name to server");
		}
		
		/* read in the world object */
		ObjectInputStream ois; 
		World world = null; 
		try{ 
			ois = new ObjectInputStream(this.in); 
			world = (World) ois.readObject(); 
			ois.close();
		}
		catch(Exception e){
			System.out.println("Unable to read object from server"); 
		}
		
		world.setName(world.getName() + "_clientcopy"); 
		
		/* save the world */
		Save.saveWorld(world);  
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<String> getWorlds(){ 
		/* read from the server */
		try{ 
			String fS = ""; 
			fS = this.in.readUTF(); //should be ?
		}
		catch(Exception e){ 
			
		}
		
		/* write to server */
		try{ 
			this.out.writeUTF("listWorlds");
		}
		catch(Exception e){ 
			
		}
		
		/* read object from server */
		ArrayList<String> worldsList = null; 
		ObjectInputStream ois; 
		try{ 
			ois = new ObjectInputStream(this.in); 
			worldsList = (ArrayList<String>) ois.readObject();
			ois.close(); 
		}
		catch(Exception e){ 
			//
		}
		
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
		ec.downloadWorld("Mushs_World");
		
		try{
			ec = new EveClient();
		}
		catch(Exception e){
			
		}
		
		Iterator<String> worldsList = ec.getWorlds();
		
		while(worldsList.hasNext()){ 
			System.out.println(worldsList.next());
		}
	}
		
}
