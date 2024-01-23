import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PointMethodsImpl extends UnicastRemoteObject implements PointMethods{
	
	
	public PointMethodsImpl() throws RemoteException {
		super();
	}
	
	@Override
	public double calcLength(Point a, Point b) throws RemoteException {
		return Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
	}
	@Override
	public double calcAreaThroughCenter(Point center, Point line) throws RemoteException {

		return Math.pow(calcLength(center, line), 2d) * Math.PI;
	}
	@Override
	public double calcPerimeterThroughCenter(Point center, Point line) throws RemoteException {

		return 2 * Math.PI * calcLength(center, line);
	}
	@Override
	public double calcArea(Point a, Point b) throws RemoteException {

		return Math.pow(calcLength(a, b), 2d) * Math.PI / 4;
	}
	@Override
	public double calcPerimeter(Point a, Point b) throws RemoteException {
		return Math.PI * calcLength(a, b) / 2;
	}
}
