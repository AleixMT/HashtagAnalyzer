
/**
 * Classe per a crear les llistes dinamiques
 * @author
 *
 * @param <T> tipus generic
 */
public class LlistaDinamica<T extends Comparable<T>> implements TADLlistaGenerica<T> {
	private ObjReferencia<T> primer;
	private int numElem;
	
	public LlistaDinamica() {
		this.primer = null;
		numElem = 0;
	}

	/**
	 * Metode per a afegir un element a la llista dinamica
	 * @param a - element a afegir
	 */
	public boolean afegir(T a) {
		if (this.primer == null) 	//si el primer encara no s'ha inicilitzat vol dir que estem al principi, llavors sera igual al ultim
		{
			this.primer = new ObjReferencia<T>(a, null);
		}
		else
		{
			ObjReferencia<T> aux = primer;
			ObjReferencia<T> preaux = null;
			while (aux!= null)
			{
				int res = aux.getObj().compareTo(a);	// Comparem l'element rebut per parametre amb el de la posicio actual
				if (res == 0)	// Si retorna 0 vol dir que es el mateix element
				{
					return false; //per tant sortim i retornem fals perque no hem d'afegir res
				}
				else if (res > 0) break;	//si retorna major que 0 vol dir que l'element al que apunta aux va alfabeticament
				// despres de l'objecte rebut per parametre, per tant sortim del bucle.
				else	// si el resultat de res es menor de -1, vol dir que l'objecte al que apunta aux va abans que l'objecte que
				// passem per parametre, per tant actualitzem els cursors d'iteracio
				{
					preaux = aux;	//ara preaux apunta al seguent (aux)
					aux = aux.getRef();	// ara aux apunta a l'element seguent, obtingut amb la referencia 
				}
			}
			if (preaux != null)
			{
				preaux.setRef(new ObjReferencia<T>(a, aux)); // Creem el nou objecte i fem que aux sigui el seguent i preaux sigui el que precedeix
			}
			else
			{
				this.setPrimer(new ObjReferencia<T>(a, aux));
			}
		}
		numElem++;	// Part constant, afegim un element
		return true; //sempre s'afegira, mai sera plena
	}
	
	/**
	 * Metode d'esborrat. Cambiem referencies per deixar
	 * @param e - element a esborrar
	 * @return retorna l'element eliminat. Null si no es troba
	 */
	public T esborrar(T e) throws LlistaBuida {
		ObjReferencia<T> aux;
		ObjReferencia<T> preaux;

		/*
		 * Iterem dinàmicament fins trobar l'objecte que apunta a l'objecte que volem esborrar.
		 * Assumim objecte únic.
		 */

		// Llancem Excepció llista buida
		if (this.esBuida()) throw new LlistaBuida();

		// Si es tracta del primer element, llavors fem apuntar l'element primer cap al segon element.
		// Si només hi ha un element li assignarem un Null, sinó next ObjectRef (aixo es fa automaticament)
		if (primer.getObj().compareTo(e) == 0)
		{
			aux = this.primer;  // Guardem l'element a aux
			this.primer = this.primer.getRef();
			this.numElem -= 1;
			return aux.getObj();
		}
		else if (this.numElem != 1)// Sinó recorrem fins trobar l'element que apunta a l'element que busquem
		{
			preaux = this.primer;
			aux = this.primer.getRef();  // Ja sabem que primer no és null
			while (aux.getObj().compareTo(e) != 0)
			{
				preaux = aux;
				aux = aux.getRef();
				if (aux == null) return null;
			}
			preaux.setRef(aux.getRef());
			this.numElem -= 1;
			return aux.getObj();
		}
		return null;

	}

	/**
	 * Busca un objecte T a la llista igual que el passat per parametre i retorna l'objecte Obj wrapper de la llista 
	 * @param c - element a consultar
	 * @return objecte a consultar o null si no el troba
	 */
	public Obj<T> consultar(T c) {
		ObjReferencia<T> aux = primer;
		while (aux != null){
			if (aux.getObj().compareTo(c) == 0) return (Obj<T>)aux;
			aux = aux.getRef();
		}
		return null;
	}
	
	/**
	 * Metode iterator. Retorna un objecte iterable d'aquesta classe
	 */
	public Iterator<T> iterator()
	{
		return new Iterator<T>(this);
	}

	/** 
	 * getters i setters
	 * @return primer
	 * @return numero d'elements
	 */
	public ObjReferencia<T> getPrimer() { return primer;}
	public int getNumElem() { return this.numElem;}
	public void setPrimer(ObjReferencia<T> primer) {this.primer = primer;}
	public boolean esPlena() {return false;}
	public boolean esBuida() {
		if (this.numElem == 0)  return true;
		else return false;
	}
	/**
	 * metode toString de la classe
	 */
	public String toString() {
		ObjReferencia<T> aux = this.primer;
		String retorn = "";
		while (aux!= null)
		{
			retorn += " " + aux.getObj();
			aux = aux.getRef();
		}
		return retorn;
	}

	
}
