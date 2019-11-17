/**
 * Classe iterator. Accedeix sequencialment als elements d'una coleccio
 * @author
 *
 * @param <T> tipus generic
 */
public class Iterator<T extends Comparable<T>> {
	private Obj<T>[] llista;	//nou atribut per guardar objectes
	private int posicioIterator; //Posicio de l'iterador
	private int numElem=0;	//nombre d'elements
	
	/**
	 * metode constructor per la llista estatica
	 * @param ll - llista
	 */
	@SuppressWarnings("unchecked")
	public Iterator(LlistaEstatica<T> ll) {
		int aux = ll.getPrimer();	// aux sera el nostre cursor temporal
		int i = 0;
		this.llista = (Obj<T>[]) new Obj[ll.getLlista().length];
		while (aux!=-1)	// mentre la referencia no sigui -1, iterarem sobre la llista
		{
			this.llista[i] = ll.getLlista()[aux];	// assignem a la posicio actual de l'index l'objecte de la seguent posicio
			i++;		// Incrementem l'index
			aux = ll.getLlista()[aux].getCursor();	// actualitzem el cursor
		}
		this.posicioIterator = 0;	// iniciem l'iterador
		this.numElem = ll.getNumElem();		// Actualitzem el nombre d'elements, que sera el mateix nombre d'elements que a la llista
	}
	
	/**
	 * metode constructor per la llista dinamica
	 * @param ll - llista
	 */
	@SuppressWarnings("unchecked")
	public Iterator (LlistaDinamica<T> ll){
		this.numElem = 0;
		this.posicioIterator = 0; 
		this.llista = (Obj<T>[]) new Obj[ll.getNumElem()];
		ObjReferencia<T> aux = ll.getPrimer();
		while (aux != null){
			this.llista[this.numElem] = (Obj<T>) aux;
			aux = aux.getRef();
			this.numElem++;
		}
	}

	/*
	part del codi principal va aqui per les diferents funcionalitats
	 */

	/**
	 * retorna cert si encara no hem arribat a l'ultim element, es a dir, si hi ha "next"
	 * @return true si hi ha un altre element, false si no.
	 */
	public boolean hasNext() {
		return (posicioIterator<this.numElem);	
	}
	
	/**
	 * Retornem el seguent element T i incrementem la posicio de l'iterador
	 * @return seguent element T
	 */
	public Obj<T> next() {
		Obj<T> aux = this.llista[posicioIterator];
		posicioIterator++;
		return aux;
	}
	
	/**
	 * metode extra que serveix per a reiniciar la posicio de l'index en l'iterador 
	 */
	public void reset(){
		this.posicioIterator=0;
	}
	
	/**
	 * metode per a obtenir el numero d'elements
	 * @return numero d'elements
	 */
	public int getNumElem(){
		return this.numElem;
	}
}
