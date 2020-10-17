import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	private Map<String, Color> chatColors = new HashMap<String, Color>();
	
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
						
						try {
							name = message.substring(0, message.indexOf(":"));
						}
						catch(Exception e) {
							name = "Server";
						}
						Color checkMap = chatColors.get(name);
						if(checkMap == null) {
							chatColors.put(name, getRandomColor());
						}
						Platform.runLater(new Runnable() {
				            @Override public void run() {
				            	incomingMessages.add(new Label(message));
				            	incomingMessages.get(index).setId("chat");
								incomingMessages.get(index).setAlignment(Pos.BOTTOM_LEFT);
								Color color = chatColors.get(name);
								incomingMessages.get(index).setStyle("-fx-background-color: rgb("+color.getRed()+","+color.getGreen()+","+color.getBlue()+")");
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
	
	public Color getRandomColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color color =  new Color(r, g, b);
		return color;
	}
	
}
