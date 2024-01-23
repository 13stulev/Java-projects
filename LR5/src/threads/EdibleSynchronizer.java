package threads;
import mainClasses.Edible;

public class EdibleSynchronizer {
    private Edible  i;
    private volatile int current = 0;
    private boolean set = false;
   
    public EdibleSynchronizer(Edible i) {
        this.i = i;
    }
   
    public synchronized double  read() throws InterruptedException {
            if (!canRead()) throw new InterruptedException();
            int[] arr = i.getMasses();
            while (!set)
                wait();
            
            System.out.println("Read: " + arr[current - 1] + " from position " + (current - 1));
            set = false;
            notifyAll();

        return arr[current - 1];
    }  
   
    public synchronized void write(int val) throws InterruptedException {

            if (!canWrite()) throw new InterruptedException();
            while (set)
                wait();
           i.setMass(val, current);
            System.out.println("Write: " + val + " to position " + current);
            set = true;
            current++;
            notifyAll();
    }
    
    public boolean canRead() {
        return current != i.getMasses().length + 1;
    }
    
    public boolean canWrite() {
        return current < i.getMasses().length;
    }
    public int getLength() {
    	return i.getMasses().length;
    }
}
