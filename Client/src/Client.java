import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	private String name;
	
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
		
		send("\\con:test");
	}
	
	public void send(String msg) {
		try {
			msg = this.name + ":" + msg + "\\e";
			byte [] data = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
