package mainClasses;
import java.io.*;
import java.util.Scanner;


public class StreamWorker {
	private static EdibleFactory factory = new HomeFoodFactory();
	public static void outputEdible(Edible ed, OutputStream out) throws IOException {
		ed.output(out);
	}
	public	static Edible inputEdible(InputStream in) throws Exception {
		DataInputStream dataInputStream = new DataInputStream(in);
		Edible ans;
		if(dataInputStream.readUTF() == "HomeFood") {	
		};
		String name = dataInputStream.readUTF();
		int count = dataInputStream.readInt();
		Ingridient[] ingridients = new Ingridient[count];
		for(int i = 0; i < count; i++) {
			ingridients[i] = DB.findIngridientByName(dataInputStream.readUTF());
		}
		count = dataInputStream.readInt();
		int[] masses = new int[count];
		for(int i = 0; i < count; i++) {
			masses[i] = dataInputStream.readInt();
		}
		return createInstance(name, ingridients, masses);
	}
	public static void writeEdible(Edible ed, Writer out) throws IOException {
		ed.write(out);
	}
	public static Edible readEdible(Reader in) throws Exception {
	BufferedReader bf = (BufferedReader)in;
	String check = bf.readLine();
	String[] tokens = check.split(" ");
	String name = tokens[1];
	int count = Integer.parseInt(tokens[2]);
	Ingridient[] ingridients = new Ingridient[count];
	int currentInd = 0;
	for(int i = 3; i < count + 3; i++) {
		ingridients[i - 3] = DB.findIngridientByName(tokens[i]);
		currentInd = i;
	}
	currentInd++;
	count = Integer.parseInt(tokens[currentInd]);
	int[] masses = new int[count];
	for(int i = currentInd + 1; i < currentInd + count; i++) {
		masses[i - currentInd - 1] = Integer.parseInt(tokens[i]);
	}
	
	return createInstance(name, ingridients, masses);
}
	public static void serializeEdible(Edible ed, OutputStream out) throws IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
	objectOutputStream.writeObject(ed);
}
	public static Edible deserializeEdible(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		Edible ans = (Edible) objectInputStream.readObject();
		return ans;
	}
	public static void writeFormatEdible (Edible ed, Writer out) {
		PrintWriter printWriter = new PrintWriter(out);
		String ans = "";
		if(ed instanceof FastFood) {
			ans += "FastFood" + "\n";
			ans += ed.getName() + "\n";
			ans += ed.getIngridients().length + "\n";
			for(Ingridient ing: ed.getIngridients()) {
				ans += ing.getName() + "\n";
			}
			ans += ed.getMasses().length + "\n";
			for(int mass: ed.getMasses()) {
				ans += mass + "\n";
			}
			} else {
				ans += "HomeFood" + "\n";
				ans += ed.getName() + "\n";
				ans += ed.getIngridients().length + "\n";
				for(Ingridient ing: ed.getIngridients()) {
					ans += ing.getName() + "\n";
				}
				ans += ed.getMasses().length + "\n";
				for(int mass: ed.getMasses()) {
					ans += mass + "\n";
				}
		}
		printWriter.print(ans);
	}
	public static Edible readFormatEdible (Scanner in) throws IOException, Exception {
		String objectInLine = in.nextLine();
		if (objectInLine == null) {
			throw new Exception("Файл пуст");
		}
		String name;
		Ingridient[] ingridients;
		int[] masses;
		if(objectInLine.equals("HomeFood")) {
			setFactory(new HomeFoodFactory());
		} else {
			setFactory(new FastFoodFactory());
		}
		name = in.nextLine();
		ingridients = new Ingridient[Integer.parseInt(in.nextLine())];
		for(int i = 0; i < ingridients.length; i++) {
			ingridients[i] = DB.findIngridientByName(in.nextLine());
		}
		masses = new int[Integer.parseInt(in.nextLine())];
		for(int i = 0; i < masses.length; i++) {
			masses[i] = Integer.parseInt(in.nextLine());
		}
		return new FastFood(name, ingridients, masses);
		}
	public static Edible synchronizedEdible (Edible ed) {
		SynchronizedEdible sed = new SynchronizedEdible(ed);
		return sed;
	}
	
	public static Edible unmodifiedEdible(Edible ed) {
		return new ImutableEdible(ed);
	}
	public static void setFactory(EdibleFactory ed) {
		factory = ed;
	}
	public static Edible createInstance() {
		return factory.createInstance();
	}
	public static Edible createInstance(String name, Ingridient[] ingridients, int[] masses) {
		return factory.createInstance(name, ingridients, masses);
	}
}
