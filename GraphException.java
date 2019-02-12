/**
 * GraphException represents any error that can occur when calling a method of the Graph class
 * @author fmoham26 Fawaz Mohammad
 *
 */
public class GraphException extends Exception {

	/**
	 * constructor
	 * @param string string to output to explain exception
	 */
	public GraphException(String string) {
		
		
		super(string);
		
	}

}
