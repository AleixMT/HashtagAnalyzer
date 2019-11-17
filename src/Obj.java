/**
 * Classe contenidora d'alumnes i assignatures
 * @author
 *
 * @param <T> tipus generic
 */
public class Obj <T extends Comparable<T>> implements Comparable<T> {
	private T obj;
	
	/**
	 * metode constructor
	 * @param obj - objecte
	 */
	public Obj(T obj) {
		this.setObj(obj);
	}

	/**
	 * Metode compareTo
	 * @return -1 si es mes petit, 1 si es mes gran i 0 si es igual
	 */
	public int compareTo(T o) {
		return this.obj.compareTo(o);
	}

	/**
	 * Getters i setters de l'objecte
	 * @return objecte
	 */
	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
