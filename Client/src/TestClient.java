
public class TestClient {

	public static void main(String[] args) {
		Client client = new Client("localhost", 7654, "Trifon");
		client.send("Trifon: Hello, this is a test.\\e");
	}

}
