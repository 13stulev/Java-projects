import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class PointServer {

	
	
	public static void main(String[] args) {
		

		try {

			PointMethodsImpl obj = new PointMethodsImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Point", obj);
			System.err.println("Server ready");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
