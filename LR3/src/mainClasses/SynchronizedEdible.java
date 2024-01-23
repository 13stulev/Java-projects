package mainClasses;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class SynchronizedEdible implements Edible {
	Edible ed;
	
	public SynchronizedEdible (Edible ed) {
		this.ed = ed;
	}
	@Override
	public synchronized double calcCalories() {
		return ed.calcCalories();
	}

	@Override
	public synchronized String showIngridients() {
		return ed.showIngridients();
	}

	@Override
	public synchronized Ingridient[] getIngridients() {
		return ed.getIngridients();
	}

	@Override
	public synchronized void setIngridients(Ingridient[] ingr) {
		ed.setIngridients(ingr);

	}

	@Override
	public synchronized String getName() {
		return ed.getName();
	}

	@Override
	public synchronized void setName(String name) {
		ed.setName(name);

	}

	@Override
	public synchronized void output(OutputStream out) throws IOException {
		ed.output(out);

	}

	@Override
	public synchronized void write(Writer out) throws IOException {
		ed.write(out);
	}

	@Override
	public synchronized int[] getMasses() {
		return ed.getMasses();
	}

	@Override
	public synchronized void setMasses(int[] masses) {
		ed.setMasses(masses);
	}

	@Override
	public synchronized void setMass(int mass, int i) {
		ed.setMass(mass, i);
	}
	@Override
	public synchronized Iterator<Integer> iterator() {
		
		return this.ed.iterator();
	}

}
