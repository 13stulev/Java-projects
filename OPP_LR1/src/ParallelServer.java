
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ParallelServer {
public static boolean quitting = false;
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(3345);
		while(!quitting) {
		Socket client = server.accept();
		System.out.println("Подключение успешно");
		FunctionLogic fL = new FunctionLogic(client);
		fL.start();
		}
		server.close();
	

	}

}
