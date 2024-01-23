package threads;

public class ImplementedThreadReader implements Runnable {
	EdibleSynchronizer s;
	public ImplementedThreadReader(EdibleSynchronizer s){
        this.s = s;
    }
	@Override
	public void run() {
		try {
			for(int i = 0; i < s.getLength(); i++)
			s.read();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
