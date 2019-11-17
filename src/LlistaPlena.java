/**
 * Excepcio. Salta quan la llista esta plena
 * @author
 *
 */
public class LlistaPlena extends Exception{

	private static final long serialVersionUID = 1L;

	public LlistaPlena(){
		super("ERROR: la llista est� plena.");
	}
}
