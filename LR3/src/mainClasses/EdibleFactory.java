package mainClasses;

public interface EdibleFactory {
	public Edible createInstance();
	public Edible createInstance(String name, Ingridient[] ingridients,int[] masses);
}
