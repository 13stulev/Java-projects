import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class PointClient {
	private PointClient() {}
	
	
	public static void main(String[] args) {
		boolean quitting = false;

	        Scanner in = new Scanner(System.in);
	        try {
	            
	            PointMethods stub = (PointMethods) Naming.lookup("rmi://localhost:1099/Point");
	            while(!quitting) {
	            	
	            	System.out.println("Введите координаты первой точки");
	            	double x = in.nextDouble();
	            	double y = in.nextDouble();
	            	Point a = new Point(x, y);
	            	System.out.println("Введите координаты второй точки");
	            	x = in.nextDouble();
	            	y = in.nextDouble();
	            	Point b = new Point(x, y);
	            	Double response;
	            
	            	System.out.println("Введите число где: 1 - вычислить расстояние между точками;\n 2 - расчет длины окружности, центром которой является одна из точек, а радиусом – расстояние между точками;\n 3 - расчет площади круга, центром которого является одна из точек, а радиусом – расстояние между точками;\n 4 - расчет длины окружности, диаметром которой является расстояние между точками;\n 5 - расчет площади круга, диаметром которого является расстояние между точками;\n 6 - выход.");
	            	switch(in.nextInt()) {
	            		case 1:
	            			response = stub.calcLength(a, b);
	            			System.out.println("Расстояние равно " + response);
	            			break;
	            		case 2:
	            			response = stub.calcPerimeterThroughCenter(a, b);
	            			System.out.println("Периметр равен " + response);
	            			break;
	            		case 3:
	            			response = stub.calcAreaThroughCenter(a, b);
	            			System.out.println("Площадь равна " + response);
	            			break;
	            		case 4:
	            			response = stub.calcPerimeter(a, b);
	            			System.out.println("Периметр равен " + response);
	            			break;
	            		case 5:
	            			response = stub.calcArea(a, b);
	            			System.out.println("Площадь равна " + response);
	            			break;
	            		case 6:
	            			quitting = true;
	            			break;
	            	};
	            }
	            in.close();
            	System.out.println("Работа завершена");
	        } catch (Exception e) {
	            System.err.println("Client exception: " + e.toString());
	            e.printStackTrace();
	        }
	}

}
