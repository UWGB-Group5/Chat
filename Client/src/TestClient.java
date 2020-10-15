
public class TestClient {

	public static void main(String[] args) {
		Client client = new Client("localhost", 7654, "Trifon");
		client.send("\\con:JUST TESTING!");
	}

}
