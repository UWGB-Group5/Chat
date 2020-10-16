import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Client {
	
	public static DatagramSocket socket;
	private InetAddress address;
	private int port;
	private String name;
	private String message;
	private int index = 0;
	private ArrayList<Label> incomingMessages = new ArrayList<>();
	
	public Client(String address, int port, String name, VBox chatBoxReceive, VBox chatBoxSend) {
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
		listen(chatBoxReceive, chatBoxSend);
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
	
	public void listen(VBox chatBoxReceive, VBox chatBoxSend) {
		Thread listenThread = new Thread("Chat Listener") {
			public void run() {
				try {
					while(true) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						String msg = new String(data);
						msg = msg.substring(0, msg.indexOf("\\e"));
						//ADD THIS MSG IN THE MSG BOX
						message = msg;
						Platform.runLater(new Runnable() {
				            @Override public void run() {
				            	incomingMessages.add(new Label(message));
								incomingMessages.get(index).setAlignment(Pos.BOTTOM_LEFT);
					            chatBoxReceive.getChildren().add(incomingMessages.get(index));
					            chatBoxSend.getChildren().add(new Label(""));
					            index++;
				            }
				        });
						
						
						
						
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
