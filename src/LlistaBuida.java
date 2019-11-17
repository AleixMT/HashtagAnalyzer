/**
 * Excepcio. Salta quan la llista esta buida
 * @author
 *
 */
public class LlistaBuida extends Exception{
	private static final long serialVersionUID = 1L;

	public LlistaBuida(){
		super("ERROR: la llista est� buida.");
	}

}
