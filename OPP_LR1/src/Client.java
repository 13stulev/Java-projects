import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws InterruptedException, IOException {
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		

			socket = new Socket("localhost", 3345);
			System.out.println("Client connected to socket.");
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

		Scanner input = new Scanner(System.in);
		System.out.println("Введите первое число");
		int a = input.nextInt();
		System.out.println("Введите второе число");
		int b = input.nextInt();
		System.out.println("Выключить ли сервер? true-да false-нет");
		boolean flag = input.nextBoolean();
		
			out.writeInt(a);
			out.writeInt(b);
			out.writeBoolean(flag);
			out.flush();
			Thread.sleep(1000);
			int c = in.readInt();

		
		System.out.println(c);
		System.out.println("Соединение сброшено");
		out.close();
		in.close();
		input.close();
		socket.close();
	}

}
