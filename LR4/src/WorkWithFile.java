import java.io.*;
import java.util.Scanner;

import exception.EmptyFile;
import exception.IndexOutOfArr;
import exception.NegativeCount;

public class WorkWithFile {
	
	public static void outputTeacherable(Teacherable o, OutputStream out) throws IOException{
		o.output(out);
	}
	
	public static Teacherable inputTeacherable(InputStream in) throws IOException, NegativeCount, IndexOutOfArr {
		Teacherable teach;
		DataInputStream dataInputStream = new DataInputStream(in);
		if (dataInputStream.readBoolean()) {
			teach = new University_teacher();
			teach.setName(dataInputStream.readUTF());
			teach.setCount(dataInputStream.readInt());
			int len = dataInputStream.readInt();
			for (int i = 0; i<len; i++) {
				teach.setElemLessons(dataInputStream.readUTF(), i);
			}
		}
		else {
			teach = new School_teacher();
			teach.setName(dataInputStream.readUTF());
			teach.setCount(dataInputStream.readInt());
			int len = dataInputStream.readInt();
			for (int i = 0; i<len; i++) {
				teach.setElemLessons(dataInputStream.readUTF(), i);
			}
		}
		return teach;
	}
	
	public static void writeTeacherable(Teacherable o, Writer out) throws IOException{
		o.write(out);
	}
	
	public static Teacherable readTeacherable(Reader in) throws IOException, NegativeCount, IndexOutOfArr, EmptyFile {
		Teacherable teach;
		BufferedReader bufferedReader = (BufferedReader) in;
		String objectInLine = bufferedReader.readLine();
		if (objectInLine == null) {
			throw new EmptyFile("Файл пустой");
		}
		String[] tokens = objectInLine.split(" ");
		if (Boolean.parseBoolean(tokens[0])) {
			teach = new University_teacher();
			teach.setName(tokens[1]);
			teach.setCount(Integer.parseInt(tokens[2]));
			int len = Integer.parseInt(tokens[3]);
			for (int i = 0; i<len; i++) {
				teach.setElemLessons(tokens[4+i],i);
			}
		}
		else {
			teach = new School_teacher();
			teach.setName(tokens[1]);
			teach.setCount(Integer.parseInt(tokens[2]));
			int len = Integer.parseInt(tokens[3]);
			for (int i = 0; i<len; i++) {
				teach.setElemLessons(tokens[4+i],i);
			}
		}
	return teach;
	}
	
	public static void serializeTeacherable (Teacherable o, OutputStream out) throws IOException{
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		objectOutputStream.writeObject(o);
	}
	
	public static Teacherable deserializeTeacherable(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		Teacherable teach = (Teacherable) objectInputStream.readObject();
		return teach;
	}
	
	public static void writeFormatTeacherable(Teacherable o, Writer out) throws IOException, NegativeCount, IndexOutOfArr{
		PrintWriter printWriter = new PrintWriter(out);
		Teacherable teacher = new School_teacher();
		String result = "";
		if (teacher.getClass() != o.getClass()) {
			result += true +"\nЭто преподаватель из университета\n";
		}
		else {
			result += false + "\nЭто преподаватель из школы\n";
		}
			result += "Имя преподавателя:\n" + o.getName() + "\n Количество учеников у него:\n" + o.getCount() +"\n"+
					"Количество предметов у него:\n" + o.getLessons().length +"\n Предметы:";
		for (String lesson : o.getLessons()) {
			result += "\n" +lesson;
		}
		printWriter.println(result);
	}
	
	public static Teacherable readFormatTeacherable(Scanner in) throws IOException, EmptyFile, NegativeCount, IndexOutOfArr {
		Teacherable teach;
		String objectInLine = in.nextLine();
		if (objectInLine == null) {
			throw new EmptyFile("Файл пустой");
		}
		else {
			if (Boolean.parseBoolean(objectInLine)) {
				teach = new University_teacher();
				in.nextLine();
				in.nextLine();
				teach.setName(in.nextLine());
				in.nextLine();
				teach.setCount(Integer.parseInt(in.nextLine()));
				in.nextLine();
				int leng = Integer.parseInt(in.nextLine());
				in.nextLine();
				for (int i = 0; i<leng; i++) {
					teach.setElemLessons( in.nextLine(),i);
				}
			}
			else {
				teach = new School_teacher();
				in.nextLine();
				in.nextLine();
				teach.setName(in.nextLine());
				in.nextLine();
				teach.setCount(Integer.parseInt(in.nextLine()));
				in.nextLine();
				int leng = Integer.parseInt(in.nextLine());
				in.nextLine();
				for (int i = 0; i<leng; i++) {
					teach.setElemLessons( in.nextLine(),i);
				}
			}
		}
		return teach;
		}

}
