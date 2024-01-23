import java.io.IOException;
import java.net.UnknownHostException;

public class ClientMain {

	public static int width = 4, height = 4;
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		final Client cli = new Client();
		cli.setVisible(true);
	}

}
