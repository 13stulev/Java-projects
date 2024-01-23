package threads;

import java.util.Random;

import mainClasses.Edible;

public class ExtendedThreadWriter extends Thread {
	private Edible ed;
	public ExtendedThreadWriter(String name, Edible ed){
        super(name);
        this.ed = ed;
    }
      
    public void run(){
        Random rand = new Random();
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        for(int i = 0; i < ed.getMasses().length; i++) {
        	int val = rand.nextInt(0,100);
			ed.setMass(val, i);
			System.out.println("Write: " + val + " to position " + i);
		}
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
}
}