/**
 * Classe filla d'obj per a una llista dinamica (utilitza referencies)
 * @author
 *
 * @param <T> tipus generic
 */
public class ObjReferencia <T extends Comparable<T>> extends Obj<T>{
	private ObjReferencia<T> ref;
	
	/**
	 * Metode constructor
	 * @param obj - objecte
	 * @param ref - referencia
	 */
	public ObjReferencia(T obj, ObjReferencia<T> ref){
		super(obj);
		this.ref = ref;
	}

	/**
	 * Getter i setter de la referencia
	 * @return referencia
	 */
	public ObjReferencia<T> getRef() {
		return ref;
	}

	public void setRef(ObjReferencia<T> ref) {
		this.ref = ref;
	}

	
	
}
