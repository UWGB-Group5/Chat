import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.net.DatagramPacket;

public class Server {
	/**
   * This is a class that controls a simple broadcast server.
   *  @param port  Server port
   */
	
	private static DatagramSocket socket;
	private static boolean running;
	private static int clientId = 0;
	private static ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();
	
	//Creates DatagramSocket object and starts listen() method.
	public static void run(int port) {
		try {
			socket = new DatagramSocket(port);
			System.out.println("Server started on port " + port);
			running = true;
			listen();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Starts a thread that listens for messages from server
	private static void listen() {
		Thread listenThread = new Thread("Chat Listener") {
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						
						//calls socket receive method to get messages from the server.
						socket.receive(packet);
						System.out.println("listen() RECIVED MSG");
						String msg = new String(data);
						msg = msg.substring(0, msg.indexOf("\\e"));
						
						//Checks if the message is a connection string or a real message.
						if(!isCommand(msg, packet)) {
							String name = msg.substring(0, msg.indexOf(":"));
							System.out.println(name);
							broadcast(msg, name);
						}
						
					}
					
				}catch(Exception e) {
					//Prints stack trace
					e.printStackTrace();
				}
			}
		};
		listenThread.start();
	}
	
	//Sends message to a socket.
	private static void send(String msg, InetAddress address, int port) {
		try {
			msg = msg + "\\e";
			byte [] data = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			System.out.println("Message was sent to: "+packet.getAddress()+":"+packet.getPort());
			socket.send(packet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Loops through all connected clients and sends them a message.
	private static void broadcast(String msg, String sender) {
		System.out.println("BROADCASTING.");
		for(ClientInfo info : clients) {
			System.out.println(info.getName());
			System.out.println(sender);
			System.out.println(sender != info.getName());
			if(!sender.equals(info.getName())) {
				send(msg, info.getAddress(), info.getPort());
			}
		}
	}
	
	//Closes server.
	public static void close(int port) {
		running = false;
	}
	
	//Checks a message if contains connection substring.
	private static boolean isCommand(String msg, DatagramPacket packet) {
		if(msg.startsWith("\\con:")) {
			String name = msg.substring(msg.indexOf(":")+1);
			clients.add(new ClientInfo(packet.getAddress(), clientId, packet.getPort(), name));
			clientId++;
			broadcast("User " + name + " connected!", "Server");
			System.out.println("User " + name + " connected!");
			return true;
		}
		return false;
	}

}
