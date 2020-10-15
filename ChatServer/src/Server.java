import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.net.DatagramPacket;

public class Server {

	private static DatagramSocket socket;
	private static boolean running;
	private static int clientId = 0;
	private static ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();
	
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
	
	private static void listen() {
		Thread listenThread = new Thread("Chat Listener") {
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						System.out.println("MSG RECIVED");
						String msg = new String(data);
						msg = msg.substring(0, msg.indexOf("\\e"));
						if(!isCommand(msg, packet)) {
							broadcast(msg);
						}
						
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		listenThread.start();
	}
	
	private static void send(String msg, InetAddress address, int port) {
		try {
			msg = msg + "\\e";
			byte [] data = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void broadcast(String msg) {
		for(ClientInfo info : clients) {
			send(msg, info.getAddress(), info.getPort());
		}
	}
	
	public static void close(int port) {
		running = false;
	}
	
	private static boolean isCommand(String msg, DatagramPacket packet) {
		if(msg.startsWith("\\con:")) {
			String name = msg.substring(msg.indexOf(":")+1);
			clients.add(new ClientInfo(packet.getAddress(), clientId, packet.getPort(), name));
			clientId++;
			broadcast("User " + name + " connected!");
			return true;
		}
		return false;
	}

}
