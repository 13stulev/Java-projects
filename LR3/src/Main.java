import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mainClasses.DB;
import mainClasses.Edible;
import mainClasses.FastFood;
import mainClasses.HomeFood;
import mainClasses.Ingridient;
import mainClasses.StreamWorker;

public class Main {

	
	public static void main(String[] args) throws Exception {
		boolean quitting = false;
		Scanner in = new Scanner(System.in);
		Edible[] food = new Edible[5];		
		int a = 0;

		System.out.println("Введите номер ЛР");
		a = in.nextInt();
		switch(a) {
		case 3: LR3(quitting, a, food, in);
		case 4: LR4(quitting, a, food, in);
		}
			
			
		}
	public static void LR3(boolean quitting, int a, Edible[] food, Scanner in) throws CloneNotSupportedException {
		a = 0;
		List<List<Edible>> list = new ArrayList<List<Edible>>();
		list.add(new ArrayList<Edible>());
		while(!quitting){
			System.out.println("Введите число где: 1 - создать массив, 2 - вывести информацию об элементах массива, 3 - вывести блюда с равным числом каллорий, 4 - разделить на однотипные элементы и вывести, 5 - вывод объявляемого исключения, 6 - выход");
			a = in.nextInt();
			
			switch(a) {
			case 1:
				for(int i = 0; i < 3; i++) {
					food[i] = (Edible)new HomeFood();
				}
				for(int i = 3; i < food.length; i++) {
					food[i] = (Edible)new FastFood();
				}
				break;
			case 2:
				for(int i = 0; i < food.length; i++) {
					System.out.println(food[i]);
				}
				break;
			case 3:
				for(Edible ed: food) {
					for(List<Edible> dishes: list) {
						if(dishes.isEmpty()) {
							dishes.add(ed);
							list.add(new ArrayList<Edible>());
							break;
						}
						if(dishes.get(0).calcCalories() == ed.calcCalories()) {
							dishes.add(ed);
							break;
						}
					}
				}
				int counter = 1;
				for(List<Edible> dishes: list) {
						if(!dishes.isEmpty()) {
							System.out.println("массив " + counter);
							for(Edible dish: dishes) {
								System.out.println(dish);
							}
							counter++;
					}
				}
				break;
			case 4:
				List<HomeFood> arrayOfHF = new ArrayList<HomeFood>();
				List<FastFood> arrayOfFF = new ArrayList<FastFood>();
				for(Edible dish: food) {
					if(dish instanceof HomeFood) {
						arrayOfHF.add((HomeFood)dish);
					} else {
						arrayOfFF.add((FastFood)dish);
					}
				}
				System.out.println("Первый массив");
				for(HomeFood dish: arrayOfHF) {
					System.out.println(dish);
				}
				System.out.println("Второй массив");
				for(FastFood dish: arrayOfFF) {
					System.out.println(dish);
				}
				break;
			case 5:
				Ingridient[] ingr =  {DB.findIngridientByName("Салат")};
				int[] arr = {-100};
				try {
				FastFood error = new FastFood("Салат", ingr, arr);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				break;
			case 6:
				quitting = true;
				in.close();
				break;
			};
	}
	}
	
	public static void LR4(boolean quitting, int a, Edible[] food, Scanner in) throws Exception {
		boolean eof = false;
		while(!quitting){
			System.out.println("Введите число где: 1 - создать массив с выбором типа объекта, 2 - запись и чтение в байтовый поток, 3 - запись и чтение в текстовый поток, 4 - сериализация и десериализация, 5 - форматный ввод/вывод, 6 - выход");
			a = in.nextInt();
			
			switch(a) {
			case 1:
				System.out.println("Введите число где: 1 выбор объекта типа HomeFood, а 2 - FastFood");
				for(int i = 0; i < 5; i++) {
					int num = in.nextInt();
					if(num == 1) {
						food[i] = new HomeFood();
					} else if (num == 2) {
						food[i] = new FastFood();
					} else {
						System.out.println("Введено неверное значение. Будет создан объект типа HomeFood");
						food[i] = new HomeFood();
					}
				}
				break;
			case 2:
				FileOutputStream fileOutputStream = new FileOutputStream("Edible.bin");
				for(Edible ed: food) {
					StreamWorker.outputEdible(ed, fileOutputStream);
				}
				System.out.println("Объекты записаны\n");
				fileOutputStream.close();
				
				FileInputStream fileInputStream = new FileInputStream("Edible.bin");
				ArrayList<Edible> foodFromFile = new ArrayList<Edible>();
				while (fileInputStream.available() > 0) {
					foodFromFile.add(StreamWorker.inputEdible(fileInputStream));
				}
				fileInputStream.close();
				System.out.println("записанные объекты\n");
				
				for(Edible ed: foodFromFile) {
					System.out.println(ed);
				}
				break;
			case 3:
				FileWriter fileWriter = new FileWriter("food.txt");
				for(Edible ed: food) {
					StreamWorker.writeEdible(ed, fileWriter);
				}
				fileWriter.close();
				System.out.println("Объекты записаны\n");
				
				BufferedReader fileReader = new BufferedReader(new FileReader("food.txt"));
				ArrayList<Edible> foodFromFileTxT = new ArrayList<Edible>();
				eof = false;
				try {
				while (!eof) {
					foodFromFileTxT.add(StreamWorker.readEdible(fileReader));
				}
				} catch (NullPointerException ex) {
					eof = true;
				}
				fileReader.close();
				for(Edible ed: foodFromFileTxT) {
					System.out.println(ed);
				}
				break;
			case 4:
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serializedFood.dat"));
				for(Edible ed: food) {
					StreamWorker.serializeEdible(ed, oos);
				}
				System.out.println("Объекты записаны\n");
				oos.close();
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serializedFood.dat"));
				ArrayList<Edible> foodFromDeserialisation = new ArrayList<Edible>();
				eof = false;
				try {
					while(!eof) {
						foodFromDeserialisation.add(StreamWorker.deserializeEdible(ois));
					}
				} catch (Exception ex) {
					eof = true;
				}
				for(Edible ed: foodFromDeserialisation) {
					System.out.println(ed);
				}
				ois.close();
				
				break;
			case 5:
				FileWriter fileFormatWriter = new FileWriter("foodFormat.txt");
				for(Edible ed: food) {
					StreamWorker.writeFormatEdible(ed, fileFormatWriter);
				}
				fileFormatWriter.close();
				System.out.println("Объекты записаны\n");
				
				BufferedReader fileFormatReader = new BufferedReader(new FileReader("foodFormat.txt"));
				Scanner scanner = new Scanner(fileFormatReader);
				ArrayList<Edible> foodFromFormatFileTxT = new ArrayList<Edible>();
				eof = false;
				try {
				while (!eof) {
					foodFromFormatFileTxT.add(StreamWorker.readFormatEdible(scanner));
				}
				}  catch (Exception ex) {
					eof = true;
				}
				for(Edible ed: foodFromFormatFileTxT) {
					System.out.println(ed);
				}
				scanner.close();
				fileFormatReader.close();
				break;
			case 6:
				quitting = true;
				in.close();
				break;
			};
	}
	}

}
