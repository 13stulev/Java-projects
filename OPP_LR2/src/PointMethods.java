import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PointMethods extends Remote{
	public double calcLength(Point a, Point b) throws RemoteException;
	public double calcAreaThroughCenter(Point center, Point line) throws RemoteException;
	public double calcPerimeterThroughCenter(Point center, Point line) throws RemoteException;
	public double calcArea(Point a, Point b) throws RemoteException;
	public double calcPerimeter(Point a, Point b) throws RemoteException;
	
}
