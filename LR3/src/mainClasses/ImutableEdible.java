package mainClasses;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public final class ImutableEdible implements Edible {
	private final Edible ed;
	public ImutableEdible(Edible ed) {
		this.ed = ed;
	}
	@Override
	public Iterator<Integer> iterator() {
		
		return ed.iterator();
	}

	@Override
	public double calcCalories() {
		return ed.calcCalories();
	}

	@Override
	public String showIngridients() {
		return ed.showIngridients();
	}

	@Override
	public Ingridient[] getIngridients() {
		return ed.getIngridients();
	}

	@Override
	public void setIngridients(Ingridient[] ingr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		
		return ed.getName();
	}

	
	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void output(OutputStream out) throws IOException {
		ed.output(out);

	}

	@Override
	public void write(Writer out) throws IOException {
		ed.write(out);

	}

	@Override
	public int[] getMasses() {
		return ed.getMasses();
	}

	@Override
	public void setMasses(int[] masses) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setMass(int mass, int i) {
		throw new UnsupportedOperationException();
	}

}
