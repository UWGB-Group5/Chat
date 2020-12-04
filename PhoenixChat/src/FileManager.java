import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {
	
	public String fileName;
	public BufferedWriter buffer;

	public FileManager(String fileName) {
		this.fileName = fileName;
	}
	
	public void setUpFile() {
		try {
		    // create a writer
			buffer = Files.newBufferedWriter(Paths.get(this.fileName),
		            StandardCharsets.UTF_8,
		            StandardOpenOption.CREATE,
		            StandardOpenOption.APPEND);

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	//write to file
	public void write(String line) throws IOException {
		buffer.write(line);
		buffer.newLine();
		buffer.flush();
	}
	
	//close file when done writing
	public void closeFile() throws IOException {
		buffer.close();
	}
}
