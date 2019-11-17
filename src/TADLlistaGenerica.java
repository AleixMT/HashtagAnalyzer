
/**
 * Interface per a definir una llista generica.
 * @author
 *
 */
public abstract interface TADLlistaGenerica<T extends Comparable<T>> {
	
	/**
	 * Afegeix un element a la llista
	 * @param a - element a afegir
	 * @throws LlistaPlena - llista plena
	 * @return false - si no s'ha afegit, true - si s'ha afegit
	 */
	public boolean afegir(T a) throws LlistaPlena;
	
	/**
	 * Esborra un element de la llista
	 * @param e - element a esborrar
	 * @return element esborrat
	 * @throws LlistaBuida - llista buida
	 */
	public T esborrar(T e) throws LlistaBuida;
	
	/**
	 * Consulta un element de la llista
	 * @param c - element a consultar
	 * @return element a consultar
	 */
	public Obj<T> consultar(T c);
	
	/**
	 * Getter del numero d'elements
	 * @return numero d'elements a la llista
	 */
	public int getNumElem();
	
	/**
	 * Crea un nou iterador per a la propia classe i ho retorna
	 * @return objecte iterable de la llista
	 */
	public Iterator<T> iterator();
	
	/**
	 * metode toString
	 * @return String amb els elements de la llista
	 */	
	public String toString();
}
