import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(3345);
		boolean quitting = false;
		
		
		while(!quitting) {
			Socket client = server.accept();
			System.out.println("Подключение успешно");
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			DataInputStream in = new DataInputStream(client.getInputStream());
			int a = in.readInt();
			System.out.println(a + " Прочитано");
			int b = in.readInt();
			System.out.println(b + " Прочитано");
			quitting = in.readBoolean();
			int c;
			try 
			{
				c = a%b;
				out.writeInt(c);
			}
			catch(ArithmeticException e) {
				System.out.println("Делить на ноль нельзя!");
			}
			out.close();
			in.close();
			client.close();
			
		}
			server.close();
		
		
	}

}
