package threads;

import mainClasses.Edible;

public class ExtendedThreadReader extends Thread {
	private Edible ed;
	public ExtendedThreadReader(String name, Edible ed){
        super(name);
        this.ed = ed;
    }
      
    public void run(){
          
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        for(int i = 0; i < ed.getMasses().length; i++) {
        	System.out.println("Read: " + ed.getMasses()[i] + " from position " + i);
		}
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
}
}