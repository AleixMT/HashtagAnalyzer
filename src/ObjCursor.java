/**
 * Classe filla d'obj per a una llista estatica (utilitza cursors)
 * @author Aleix Marine i Cristina Izquierdo
 *
 * @param <T> tipus generic (Alumne/Assignatura)
 */
public class ObjCursor <T extends Comparable<T>> extends Obj<T>{
	private int cursor;
	
	/**
	 * Metode constructor
	 * @param obj - objecte
	 * @param cursor - cursor
	 */
	public ObjCursor (T obj, int cursor){
		super(obj);
		this.cursor = cursor;
	}

	/**
	 * Getter i setter del cursor
	 * @return cursor
	 */
	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
}
