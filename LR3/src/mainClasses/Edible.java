package mainClasses;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
public interface Edible extends Iterable<Integer> {
	public double calcCalories();
	public String showIngridients();
	public Ingridient[] getIngridients();
	public void setIngridients(Ingridient[] ingr);
	public String getName();
	public void setName(String name);
	void output(OutputStream out) throws IOException;
	void write(Writer out) throws IOException;
	public int[] getMasses();
	public void setMasses(int[] masses);
	public void setMass(int mass, int i);
}
