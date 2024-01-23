package mainClasses;
import java.io.*;
import java.util.Iterator;

public class HomeFood implements Edible, Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Ingridient[] ingridients;
	private int[] massesOfIngr;
	
	public HomeFood() throws CloneNotSupportedException {
		this.name = "Пахлава";
		ingridients = new Ingridient[]{DB.findIngridientByName("Мука"),
				DB.findIngridientByName("сметана"),
				DB.findIngridientByName("Сахар"),
				DB.findIngridientByName("Грецкий_орех"),
				DB.findIngridientByName("Яйцо")};
		massesOfIngr = new int[]{350, 200, 500, 500, 50};

	}
	
	public HomeFood(String name, Ingridient[] ingridients, int[] masses ) throws CloneNotSupportedException,Exception {
		this.name = name;
		this.ingridients = new Ingridient[ingridients.length];
		this.massesOfIngr = new int[masses.length];
		for(int i = 0; i < ingridients.length; i++) {
			if(masses[i] < 0) {throw new Exception("Масса не может быть отрицательной");}
			this.ingridients[i] = (Ingridient)ingridients[i].clone();
			this.massesOfIngr[i] = masses[i];
		}
	}
	
	private boolean containsIngridient(Ingridient ingridient) {
		boolean ans = false;
		for(int i = 0; i < this.ingridients.length; i++) {
			if(ingridient.equals(ingridients[i])) {
				ans = true;
			}
		}
		return ans;
	}
	public int[] getMasses() {
		return massesOfIngr;
	}
	
	@Override
	public double calcCalories() {
		double ans = 0;
		for(int i = 0; i < this.ingridients.length; i++) {
			ans += ((ingridients[i].getCarbohydrates() + ingridients[i].getProtein()) * 4 + ingridients[i].getFat() * 9) * massesOfIngr[i] / 100;
		}
		return (int)ans;
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public String showIngridients() {
		String ans = "";
		for(int i = 0; i < this.ingridients.length; i++) {
			ans += ingridients[i].getName() + ", ";
		}
		ans.replace('_', ' ');
		return ans;
	}
	
	public Ingridient[] getIngridients() {
		return this.ingridients;
	}
	public Ingridient getIngridient(int index) throws IndexOutOfBoundsException {
		try {
			return this.ingridients[index];
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		String ans = "";
		ans += "Название: " + this.name +"\n";
		ans += "Ингридиенты: " + this.showIngridients() + "\n";
		ans += "Калорийность:" + this.calcCalories() + "\n";
		ans.replace('_', ' ');
		return ans;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof HomeFood)) return false;
		HomeFood homeFood = (HomeFood)obj;
		if (homeFood.name.toLowerCase() != this.name.toLowerCase()) return false;
		for(int i = 0; i < this.ingridients.length; i++) {
			if(!this.containsIngridient(homeFood.getIngridients()[i])) {return false;}
		}
		return true;
	}
	
	
	
	@Override
    public int hashCode(){
		int ans = (int)this.calcCalories();
        return name.hashCode() + ans * 37;
    }

	@Override
	public void output(OutputStream out) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(out);
		dataOutputStream.writeUTF("HomeFood");
		dataOutputStream.writeUTF(name);
		dataOutputStream.writeInt(ingridients.length);
		for(Ingridient ing: ingridients) {
			dataOutputStream.writeUTF(ing.getName());
		}
		dataOutputStream.writeInt(massesOfIngr.length);
		for(int mass: massesOfIngr) {
			dataOutputStream.writeInt(mass);
		}
	}

	@Override
	public void write(Writer out) {
		PrintWriter printWriter = new PrintWriter(out);
		String ans = "";
		ans = "HomeFood " + name + " " + ingridients.length + " ";
		for(Ingridient ing: ingridients) {
			ans += ing.getName() + " ";
		}
		ans += massesOfIngr.length + " ";
		for(int mass: massesOfIngr) {
			ans += mass + " ";
		}
		ans += "\n";
		printWriter.print(ans);
		
	}

	@Override
	public void setIngridients(Ingridient[] ingr) {
		this.ingridients = ingr.clone();
	}

	@Override
	public void setMasses(int[] masses) {
		this.massesOfIngr = masses.clone();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setMass(int mass, int i) {
		massesOfIngr[i] = mass;
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ImplIterable(massesOfIngr);
	}

	
}
