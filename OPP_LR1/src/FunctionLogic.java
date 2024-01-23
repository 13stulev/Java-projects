import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FunctionLogic extends Thread {
	Socket client;
	boolean quitting;
	public FunctionLogic(Socket client) {
		this.client = client;

	}
	
	public void run() {
		
		DataOutputStream out;
		try {
			out = new DataOutputStream(client.getOutputStream());
			DataInputStream in = new DataInputStream(client.getInputStream());
			int a = in.readInt();
			System.out.println(a + "Прочитано");
			int b = in.readInt();
			System.out.println(b + "Прочитано");
			ParallelServer.quitting = in.readBoolean();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
