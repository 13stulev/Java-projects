import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.swing.ImageIcon;

public class Server {

	private static final int port = 3345;
	private static String[] iconIndexes;
	private static String[] pics = {"source/adventure.png", "source/biscuits.png", "source/collage.png", "source/devianart.png", "source/dog-bowl.png", "source/edvard-munch.png", "source/exhibition.png", "source/fish-food.png",
			"source/flash-on.png", "source/food-cart.png", "source/french-fries.png", "source/gallery.png", "source/google-photos-new.png", "source/hammer.png", "source/hello-kitty.png", "source/hogwarts.png",
			"source/image-clouds.png", "source/lettering.png", "source/millennium-rod.png", "source/mortal-kombat.png", "source/movie-projector.png", "source/photo-reel.png", "source/picasso.png", "source/pixar-lamp.png",
			"source/spiderman-new.png", "source/statue.png", "source/street-food.png", "source/superstar.png", "source/take-away-food.png", "source/vegan-food.png", "source/waiter.png", "source/wheat.png"};
	private static int width;
	private static int height;
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(port);
		boolean quitting = false;
		System.out.println("Сервер запущен");
		
		while(true) {
			Socket client = server.accept();
			System.out.println("Подключение успешно");
			process(client);
		}
	}
	
	private static void process(Socket client) throws IOException {
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		DataInputStream in = new DataInputStream(client.getInputStream());
		width = in.readInt();
		height = in.readInt();
		iconIndexes = new String[width * height];
		setIcons();
		for(int i = 0; i < iconIndexes.length; i++) {
			out.writeUTF(iconIndexes[i]);
		}
		in.close();
		out.close();
	}
	
	
	private static void setIcons() {
		int k = 0;
		for (int i = 1; i < width * height; i+=2) {
			iconIndexes[i] = pics[k];
			iconIndexes[i - 1] = pics[k];
			k++;
		}
		shuffle();
	}
	private static void shuffle() {
		Random rand = new Random();
		String temp;
		for(int i = 0; i < width * height; i++) {
			int k = rand.nextInt(width * height);
			temp = iconIndexes[i];
			iconIndexes[i] = iconIndexes[k];
			iconIndexes[k] = temp;
		}
	}
}
