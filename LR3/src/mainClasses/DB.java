package mainClasses;

public class DB {
	public static Ingridient[] ingridients = {
			new Ingridient("Мука", 8, 1, 81),
			new Ingridient("Грецкий_орех", 15, 65, 7),
			new Ingridient("Мед", 1, 0, 82),
			new Ingridient("Мука", 13, 11, 1),
			new Ingridient("Сливочное_масло", 0, 82, 1),
			new Ingridient("Оливковое_масло", 0, 100, 0),
			new Ingridient("Сахар", 0, 0, 100),
			new Ingridient("Салат", 1, 0, 2),
			new Ingridient("Помидор", 1, 0, 4),
			new Ingridient("Плавленый_сыр", 18, 60, 2),
			new Ingridient("Твердый_сыр", 26, 27, 2),
			new Ingridient("Котлета", 20, 23, 0),
			new Ingridient("Булочка_с_кунжутом", 9.6f, 4.2f, 59.5f),
			new Ingridient("Кетчуп", 2, 1, 22),
			new Ingridient("Чеснок", 6, 0, 30),
			new Ingridient("Сладкий_перец", 1, 0, 6),
			new Ingridient("Спагетти", 12, 1, 70),
			new Ingridient("Сметана", 3, 10, 3),
			new Ingridient("Яйцо", 13, 11, 1),
	};
	public static Ingridient findIngridientByName(String name) throws CloneNotSupportedException {
		for(Ingridient ingridient: ingridients) {
			if(name.toLowerCase().equals(ingridient.getName().toLowerCase())) {
				Ingridient ing = ingridient.clone();
				return ing;
			}
		}
		return null;
	}
}
