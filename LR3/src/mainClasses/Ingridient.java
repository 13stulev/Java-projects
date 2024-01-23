package mainClasses;
import java.io.Serializable;

public class Ingridient implements Cloneable, Serializable{
	private String name;
	private float protein, fat, carbohydrates;
	
	public Ingridient(String name, float protein, float fat, float carbohydrates) {
		this.name = name;
		this.fat = fat;
		this.protein = protein;
		this.carbohydrates = carbohydrates;
	}

	public float getFat() {
		return fat;
	}

	public float getProtein() {
		return protein;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ingridient)) return false;
		Ingridient ing = (Ingridient) obj;
		if(ing.getName().equals(this.name)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	 public Ingridient clone() throws CloneNotSupportedException {
	 Ingridient ingridient = (Ingridient) super.clone();
	 return ingridient;
	 }

}
