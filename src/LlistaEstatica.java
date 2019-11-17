
/**
 * Classe per a crear les llistes estatiques
 * @author
 *
 * @param <T> tipus generic
 */
public class LlistaEstatica<T extends Comparable<T>> implements TADLlistaGenerica<T> {
	private ObjCursor<T>[] llista;
	private int numElem;
	private int primer;
	
	private int[] buits;
	private int numElemsbuits;
	
	/**
	 * metode constructor
	 * @param dim - dimensio de la llista
	 */
	@SuppressWarnings("unchecked")
	public LlistaEstatica(int dim) {
		this.llista = (ObjCursor<T>[]) new ObjCursor[dim];
		this.primer = -1;
		this.numElem = 0;
		
		this.buits = new int[dim];
		
		//Anem carregant les posicions buides. Desapilarem a la posicio numElemsBuits, de tal manera que les carregarem
		//a la inversa, tenint les posicions buides amb menor index en les posicions de major index en buits[]
		for (int i = buits.length-1; i > -1; i--)
		{
			this.buits[i] = this.buits.length - i - 1;
		}
		this.numElemsbuits = dim;
	}

	public boolean afegir(T a) throws LlistaPlena {
		int posicio;
		int preaux = -1; // preaux fara referencia a l'element just anterior. Inicialitzem en -1 de manera arbitraria		
		int aux = this.primer;	// aux salvara la posicio del primer		
		if (!this.esPlena())	//Si no esta plena, afegim un element
		{
			if (this.esBuida()) //si la llista esta buida procedirem diferent
			{
				posicio = this.desapilarBuits();	// Treiem un enter de la llista de buits
				this.primer = posicio;				//Fem que el primer apunti a aquesta posicio (ja que sera la primera)
				this.llista[posicio] = new ObjCursor<T>(a, -1);		// En aquesta posicio creem un nou objecte ObjCursor, passant-li l'objecte
				// rebut per parametre i com a referencia posarem -1, ja que sera l'ultim element
				return true; // Com hem afegit, sortim
			}
			else
			{
				while (aux != -1)	// mentre aux (ultim element consultat) no sigui l'ultim element (no apunti a -1)
				{
					int res = this.llista[aux].getObj().compareTo(a);	// Comparem l'element rebut per parametre amb el de la posicio actual
					if (res == 0)	// Si retorna 0 vol dir que es el mateix element
					{
						return false; //per tant sortim i retornem fals perque no hem d'afegir res
					}
					else if (res > 0) break;//si retorna major que 0 vol dir que l'element al que apunta aux va alfabeticament despres de l'objecte rebut per parametre
					{
						preaux = aux;	//ara preaux apunta al seguent (aux)
						aux = this.llista[aux].getCursor();	// ara aux apunta a l'element seguent, aquest cursor 
						// l'obtenim preguntant-li el seu cursor a l'element al que apuntava aux abans d'actualitzar-se.
					}
				}
			}
			/*
			 Ja hem trobat l'element. Hem de conectar les referencies de tal manera que: preaux -> a -> aux
			 Tambe pot passar que sigui la primera iteracio i per tant calgui actualitzar el primer element. 
			 Per tant actualitzarem segons: primer -> a -> aux (l'antic primer)
			 */
			posicio = this.desapilarBuits();	// Obtenim la nova posicio on posarem el nou element
			if (preaux == -1)	// Si preaux == -1, vol dir que es la primera iteracio , per tant cal actualitzar el primer.
			{
				this.primer = posicio;	// i fem que el primer sigui la posicio on hem d'inserir el primer element
			}
			else
			{
				this.llista[preaux].setCursor(posicio);	// Assignem el cursor, que apuntara a la nova posicio
			}
			this.llista[posicio] = new ObjCursor<T>(a, aux);	// En la nova posicio buida creem un nou objecte. La referencia sera 
			//a aux llavors el cicle esta tancat. aux pot ser el seguent element o be pot ser -1, si s'ha arribat al final de la taula
			return true;	// Sortim
		}
		else
		{
			throw new LlistaPlena();
		}
		
	}

	/**
	 * Rep un objecte generic per parametre i el busca al llarg de tota la llista.
	 * Si el troba el retorna, sino retorna null.
	 */
	public Obj<T> consultar(T c) {
		int aux = this.primer;
		while (aux != -1)
		{
			if (this.llista[aux].getObj().compareTo(c) == 0) return (Obj<T>)this.llista[aux];
			else aux = llista[aux].getCursor();
		}
		return null;
	}

	/**
	 * Esborrem un element i el retornem. Retornem null si no el trobem.
	 * @param e - element a esborrar
	 * @return
	 * @throws LlistaBuida
	 */
	public T esborrar(T e) throws LlistaBuida {
		if (this.esBuida()) throw new LlistaBuida();  // El primer no apuntara a OutOfBound si no esta buida

		int aux = this.primer;  // Sera la posicio de l'element a eliminar

		// Loop unroll de la primera iteració (per si esta al principi)
		if (this.llista[aux].getObj().compareTo(e) == 0)  // Si es el primer element...
		{
			this.primer = this.llista[aux].getCursor();  // Apuntem el primer element a on esta el segon (o buit)
			this.apilarBuits(aux);  // i apilem un nou espai buit
			return this.llista[aux].getObj();
		}
		// Si no es el primer, entrem al loop de recorregut.
		// Inicialitzem aux al segon element i preaux al primer. (següent iteracio en loop unroll)
		int preaux = this.primer;  // Conte la posicio on es troba l'element previ al que volem eliminar
		aux = this.llista[preaux].getCursor();  // Conte la posicio de l'element que volem eliminar
		while (aux != -1)
		{
			if (this.llista[aux].getObj().compareTo(e) == 0)
			{
				// En aquest punt aux es la posicio de l'element que volem eliminar
				// Obtenim la posicio del seguent element a aux i la setegem com el seguent element de preaux
				this.llista[preaux].setCursor(this.llista[aux].getCursor());
				// apilem el buit que ha deixat </3
				this.apilarBuits(aux);
				return this.llista[aux].getObj();
			}
			else
			{
				// Desplacem indexos
				preaux = aux;
				aux = llista[aux].getCursor();
			}
		}
		// En aquest punt es que no l'hem trobat enlloc
		return null;
	}

	/**
	 * metode toString de la classe
	 */
	@Override
	public String toString() {
		String s = "";
		int aux = this.primer;
		while (aux != -1){
			s += " " + this.llista[aux].getObj();
			aux = this.llista[aux].getCursor();
		}
		return s;
	}


	/**
	 * Serveix per a obtenir una nova posicio lliure de la taula per a emmagatzemar un nou element.
	 * Tambe decrementa el nombre d'elements en la pila de buits 
	 * @return retorna un int, que es la posicio on pot ser inserit l'element
	 */
	public int desapilarBuits(){
		this.numElemsbuits--;
		this.numElem++;
		return this.buits[this.numElemsbuits];
	}
	
	/**
	 * Serveix per a guardar una posicio lliure de la taula per a emmagatzemar un nou element.
	 * Tambe augmenta el nombre d'elements disponibles en la pila de buits 
	 * @param n - numero d'elements buits
	 */
	public void apilarBuits(int n){
		this.numElem--;
		this.buits[this.numElemsbuits] = n;
		this.numElemsbuits++;
	}
	
	/**
	 * Serveix per a comprovar que la taula de buits estigui plena.
	 * Si ho esta retornem un boolea que ho indica
	 * @return true si esta plena, false si esta buida
	 */
	public boolean buitsEsPlena()
	{
		return this.buits.length == this.numElemsbuits;
	}
	
	/**
	 * Serveix per a comprovar que la taula de buits estigui buida
	 * @return true si esta buida, false si esta buida
	 */
	public boolean buitsEsBuida()
	{
		return this.numElemsbuits==0;
	}
	
	/**
	 * comprova que la taula principal d'elements generics estigui plena
	 * @return true si esta plena, sino false
	 */
	public boolean esPlena()
	{
		return this.llista.length == this.numElem;
	}
	
	/**
	 * comprova que la taula principal d'elements generics estigui buida
	 * @return true si esta buida, sino false
	 */
	public boolean esBuida()
	{
		return this.numElem == 0;
	}
	
	/**
	 * Crea un nou iterador per a la propia classe i ho retorna
	 * @return objecte iterable de la llista
	 */
	public Iterator<T> iterator()
	{
		return new Iterator<T>(this);
	}
	
	/**
	 * Getter del nombre d'elements a la llista
	 * @return numero d'elements a la llista
	 */
	public int getNumElem(){
		return this.numElem;
	}
	
	/**
	 * Getter de la llista
	 * @return llista
	 */
	public ObjCursor<T>[] getLlista() {
		return llista;
	}

	/**
	 * setter de la llista
	 * @param llista - llista
	 */
	public void setLlista(ObjCursor<T>[] llista) {
		this.llista = llista;
	}

	/**
	 * Getter del primer element
	 * @return primer element
	 */
	public int getPrimer() {
		return primer;
	}

	/**
	 * Setter del primer element
	 * @param primer - primer element
	 */
	public void setPrimer(int primer) {
		this.primer = primer;
	}

	/**
	 * Getter dels espais buits
	 * @return espais buits
	 */
	public int[] getBuits() {
		return buits;
	}

	/**
	 * Setter dels espais buits
	 * @param buits - espais buits
	 */
	public void setBuits(int[] buits) {
		this.buits = buits;
	}

	/**
	 * Getter del numero d'espais buits
	 * @return numero d'espais buits
	 */
	public int getNumElemsbuits() {
		return numElemsbuits;
	}

	/**
	 * Setter del numero d'espais buits
	 * @param numElemsbuits - numero d'espais buits
	 */
	public void setNumElemsbuits(int numElemsbuits) {
		this.numElemsbuits = numElemsbuits;
	}

	/**
	 * Setter del numero d'elements
	 * @param numElem - numero elements
	 */
	public void setNumElem(int numElem) {
		this.numElem = numElem;
	}
	
}
