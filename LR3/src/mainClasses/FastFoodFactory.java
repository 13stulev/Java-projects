package mainClasses;

public class FastFoodFactory implements EdibleFactory {

	@Override
	public Edible createInstance() {
		Edible ed;
		try {
			ed = new FastFood();
			return ed;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Edible createInstance(String name, Ingridient[] ingridients, int[] masses) {
		Edible ed;
			try {
				ed = new FastFood(name, ingridients, masses);
				return ed;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

}
