import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mainClasses.*;
import threads.EdibleSynchronizer;
import threads.ExtendedThreadReader;
import threads.ExtendedThreadWriter;
import threads.ImplementedThreadReader;
import threads.ImplementedThreadWriter;
public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Edible ed = new HomeFood();
		System.out.println("Main thread started...");
		Scanner in = new Scanner(System.in);
		int a = 0;
		boolean quitting = false;
		while(!quitting){
			System.out.println("Введите число где: 1 - Работа наследованных потоков, 2 - Работа имплементированных потоков, 3 - работа с оболочкой интерфейса, 4 - выход");
			a = in.nextInt();
			
			switch(a) {
			case 1:
				ExtendedThreadReader eReader = new ExtendedThreadReader("eReader",ed);
				ExtendedThreadWriter eWriter = new ExtendedThreadWriter("eWriter",ed);
				eReader.start();
				eWriter.start();
				break;
			case 2:
				EdibleSynchronizer s = new EdibleSynchronizer(ed);
				ImplementedThreadWriter writer = new ImplementedThreadWriter(s);
		        ImplementedThreadReader reader = new ImplementedThreadReader(s);
		        new Thread(writer).start();
		        try {
		        new Thread(reader).start();
		        } catch (IndexOutOfBoundsException ex) {
		        	System.out.println(ex.getMessage());
		        } 
				break;
			case 3:
				SynchronizedEdible sed = new SynchronizedEdible(ed);
				ExtendedThreadReader seReader = new ExtendedThreadReader("seReader", sed);
				ExtendedThreadWriter seWriter = new ExtendedThreadWriter("seWriter", sed);
				seReader.setPriority(Thread.MAX_PRIORITY);
				seReader.start();
				seWriter.start();
				break;
			case 4:
				quitting = true;
				in.close();
				break;
			};
			
	}
		System.out.println("Main thread finished...");
}}
