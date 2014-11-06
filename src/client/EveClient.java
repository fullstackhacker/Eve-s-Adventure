package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
		this.server = new Socket("localhost", 5000); 
		System.out.println("Connected to Server..."); 
		this.in = new DataInputStream(this.server.getInputStream()); 
		this.out = new DataOutputStream(this.server.getOutputStream()); 
	}
	
	public void uploadWorld(World world){ 
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
			String tS = "uploadWorld"; 
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
		
		/* Write file to server */
		ObjectOutputStream oos;
		try{ 
			oos = new ObjectOutputStream(this.out); 
			oos.writeObject(world); 
			oos.close(); 
		}
		catch(Exception e){ 
			
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
	
	/* Main method just to Test */
	public static void main(String[] args){ 
		EveClient ec = null; 
		try {
			ec = new EveClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		World w = new World("world1", 2,2);
		Save.saveWorld(w); 
		ec.downloadWorld("world1");
	}
		
}
