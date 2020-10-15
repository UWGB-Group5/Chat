import java.net.InetAddress;

public class ClientInfo {
	private InetAddress address;
	private int port;
	private int id;
	private String name;
	
	public ClientInfo(InetAddress address, int id, int port, String name) {
		this.address = address;
		this.id = id;
		this.port = port;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public InetAddress getAddress() {
		return this.address;
	}
	
	public int getPort() {
		return this.port;
	}
}
