import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	private static DatagramSocket socket;
	private InetAddress address;
	private int port;
	private String name;
	private String message;
	
	public Client(String address, int port, String name) {
		try {
			this.address = InetAddress.getByName(address);
			this.port = port;
			this.name = name;
			
			socket = new DatagramSocket();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		send("\\con:"+this.name);
		listen();
	}
	
	public void send(String msg) {
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
	
	public void listen() {
		Thread listenThread = new Thread("Chat Listener") {
			public void run() {
				try {
					while(true) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						String msg = new String(data);
						msg = msg.substring(0, msg.indexOf("\\e"));
						message = msg;
						//ADD THIS MSG IN THE MSG BOX
						System.out.println(msg);
						
						
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		};
		listenThread.start();
	}
	
	public String GetMessage()
	{
		return message;
	}
	
}
