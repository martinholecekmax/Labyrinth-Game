package interpreter;

/**
 * This exception is thrown when semantic errors are encountered.
 * 
 * @author Dave Voorhis
 */
public class ExceptionSemantic extends Error {

	static final long serialVersionUID = 0;

	public ExceptionSemantic(String message) {
		super(message);
	}
}
