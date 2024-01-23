import java.util.*;
import java.util.Iterator;

import mainClasses.*;


public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		StreamWorker.setFactory(new HomeFoodFactory());
		Edible[] edibles = new  Edible[5];
		for(int i = 0; i < 3; i++) {
			edibles[i] = StreamWorker.createInstance();
		}
		StreamWorker.setFactory(new FastFoodFactory());
		for(int i = 3; i < 5; i++) {
			edibles[i] = StreamWorker.createInstance();
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.println(edibles[i]);
		}
		
		Iterator<Integer> iterator = edibles[0].iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		iterator = edibles[0].iterator();
		for(Integer i: edibles[0]) {
			System.out.println(i);
		}
	}

}
