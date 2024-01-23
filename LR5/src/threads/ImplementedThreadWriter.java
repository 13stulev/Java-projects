package threads;

import java.util.Random;

public class ImplementedThreadWriter implements Runnable{
	private EdibleSynchronizer s;
	public ImplementedThreadWriter(EdibleSynchronizer s){
        this.s = s;
    }
	@Override
	public void run() {
		Random rand = new Random();
		try {
			for(int i = 0; i < s.getLength(); i++)
			s.write(rand.nextInt(0, 100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
