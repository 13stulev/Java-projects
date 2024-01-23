package classPackage;
public class Vector {
	private float[] coordinates;
	public Vector(int numOfElems, float length) {
		coordinates = new float[numOfElems];
		coordinates[0] = length;
		for(int i = 1; i < coordinates.length; i++) {
			coordinates[i] = 0;
		}
	}
	
	public static void sumOfVectors(Vector firstVector, Vector secondVector) {
		if (firstVector.getNumOfElements() == secondVector.getNumOfElements()) {
			for(int i = 0; i < firstVector.getNumOfElements(); i++){
				System.out.print(firstVector.getCoordinate(i) + secondVector.getCoordinate(i) + " ");
			}
			System.out.println();
		} else {
			System.out.println("Вектора разных размерностей");
		}
	}
	
	public static void multOfVectors(Vector firstVector, Vector secondVector) {
		if (firstVector.getNumOfElements() == secondVector.getNumOfElements()) {
			float sum = 0;
			for(int i = 0; i < firstVector.getNumOfElements(); i++){
				sum += firstVector.getCoordinate(i) * secondVector.getCoordinate(i);
			}
			System.out.println(Math.sqrt(sum));
		} else {
			System.out.println("Вектора разных размерностей");
		}
	}
	
	public float getCoordinate(int i) {
		return coordinates[i];
	}
	
	public void getCoordinates() {
		for(int i = 0; i < coordinates.length; i++){
			System.out.print(coordinates[i] + " ");
		}
		System.out.println();
	}


	public void setCoordinate(int i, float x) {
		coordinates[i] = x;
	}
	public int getNumOfElements() {
		return coordinates.length;
	}
	public float getMin() {
		float min = coordinates[0];
		for(float coordinate: coordinates){
			if(coordinate < min) {
				min = coordinate;
			}
		}
		return min;
	}
	public float getMax() {
		float max = coordinates[0];
		for(float coordinate: coordinates){
			if(coordinate > max) {
				max = coordinate;
			}
		}
		return max;
	}
	public float getNorm() {
		float norm = 0;
		for(float coordinate: coordinates){
			norm += coordinate*coordinate;
		}
		return norm;
	}
	public void multiplyBy(float num) {
		for(int i = 0; i < coordinates.length; i++){
			coordinates[i] *= num;
		}
	}
	public double getEuclidsNorm() {
		float ans = 0;
		for(int i = 0; i < coordinates.length; i++){
			ans = coordinates[i] * coordinates[i];
		}
		return Math.sqrt(ans);
	}
	public void sort() {
		for(int i = 0; i < coordinates.length; i++) {
			for(int j = 0; j + 1 < coordinates.length - i; j++) {
				if(coordinates[j] > coordinates[j + 1]) {
					float temp = coordinates[j];
					coordinates[j] = coordinates[j + 1];
					coordinates[j + 1] = temp;
				}
			}
		}
		}
}
