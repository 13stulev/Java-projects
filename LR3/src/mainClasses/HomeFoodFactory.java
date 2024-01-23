package mainClasses;

public class HomeFoodFactory implements EdibleFactory {

	@Override
	public Edible createInstance() {
		try {
			Edible ed = new HomeFood();
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
				ed = new HomeFood(name, ingridients, masses);
				return ed;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

}
