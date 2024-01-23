import classPackage.*;

public class Program {
 
	public static void main(String argvs[]) {
		Vector vector = new Vector(2, 2);
		vector.setCoordinate(1, 1.5f);
		
		System.out.print("Начальный вектор "); 
		vector.getCoordinates();
		System.out.println("Минимальное и максимальное значение " + vector.getMin() + " " + vector.getMax());
		
		vector.sort();
		System.out.print("Отсортированный вектор "); 
		vector.getCoordinates();
		
		vector.multiplyBy(2);
		System.out.print("Умноженный вектор "); 
		vector.getCoordinates();
		
		System.out.println("Евклидова норма " + vector.getEuclidsNorm()); 
		
		Vector secondVector = new Vector(2, 3); // Вектор (3, 0)
		System.out.print("Сумма векторов ");
		Vector.sumOfVectors(vector, secondVector);
		System.out.print("Скалярное произведение векторов ");
		Vector.multOfVectors(vector, secondVector);
	}
	
}